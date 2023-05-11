package com.app.projectjar.service.suggest;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestLike;
import com.app.projectjar.repository.file.suggest.SuggestFileRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestLikeRepository;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import com.app.projectjar.type.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("suggest") @Primary
@Slf4j
public class SuggestServiceImpl implements SuggestService {
    private final SuggestRepository suggestRepository;

    private final SuggestFileRepository suggestFileRepository;

    private final MemberRepository memberRepository;

    private final SuggestLikeRepository suggestLikeRepository;

    private final SuggestReplyRepository suggestReplyRepository;


    // 제안 게시판 등록
    @Override @Transactional
    public void register(SuggestDTO suggestDTO, Long memberId) {
        List<FileDTO> fileDTOS = suggestDTO.getFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> suggestDTO.setMemberDTO(toMemberDTO(member))
        );

        suggestRepository.save(toSuggestEntity(suggestDTO));
        if(fileDTOS != null){
            for (int i = 0; i < fileDTOS.size(); i++) {
                if(i == 0){
                    fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                }else {
                    fileDTOS.get(i).setFileType(FileType.NORMAL);
                }
                fileDTOS.get(i).setSuggest(getCurrentSequence());
                suggestFileRepository.save(toSuggestFileEntity(fileDTOS.get(i)));
            }
        }
    }

//    개인 챌린지 제안 게시판 목록
    @Override
    public Page<SuggestDTO> getPersonalSuggestList(int page) {
        Page<Suggest> suggests = suggestRepository.findByPersonalWithPaging_QueryDsl(PageRequest.of(page, 16));
        List<SuggestDTO> suggestDTOS = suggests.getContent().stream()
                .map(this::toSuggestDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(suggestDTOS, suggests.getPageable(), suggests.getTotalElements());
    }

    //    그룹 챌린지 제안 게시판 목록
    @Override
    public Page<SuggestDTO> getGroupSuggestList(int page) {
        Page<Suggest> suggests = suggestRepository.findByGroupWithPaging_QueryDsl(PageRequest.of(page, 16));
        List<SuggestDTO> suggestDTOS = suggests.getContent().stream()
                .map(this::toSuggestDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(suggestDTOS, suggests.getPageable(), suggests.getTotalElements());
    }

    //    상세보기
    @Override
    public SuggestDTO getSuggest(Long suggestId) {
        Optional<Suggest> suggest = suggestRepository.findByIdSuggest_QueryDsl(suggestId);
        return toSuggestDTO(suggest.get());
    }

//    현재 시퀀스 가져오기
    @Override
    public Suggest getCurrentSequence() {
        return suggestRepository.getCurrentSequence();
    }

    // 좋아요 갯수
    @Override
    public Integer getLikeCount(Long suggestId) {
        return suggestReplyRepository.getReplyCount(suggestId).intValue();
    }

    // 댓글 갯수
    @Override
    public Integer getReplyCount(Long suggestId) {
        return suggestLikeRepository.getSuggestLikeCount(suggestId).intValue();
    }

    //하트 ++
    @Override
    public void heartUp(Long memberId, Long suggestId) {
        memberRepository.findById(memberId).ifPresent(
            member -> suggestRepository.findById(suggestId).ifPresent(
                    suggest -> {
                        SuggestLike suggestLike = SuggestLike.builder()
                                .member(member)
                                .suggest(suggest)
                                .build();
                        suggestLikeRepository.save(suggestLike);
                        suggest.setSuggestLikeCount(getLikeCount(suggestId));
                    }
            )
        );
    }

//    하트 --
    @Override
    public void heartDown(Long memberId, Long suggestId) {
        suggestLikeRepository.deleteByMemberIdAndSuggestId(suggestId,memberId);
        suggestRepository.findById(suggestId).ifPresent(
                suggest -> suggest.setSuggestLikeCount(getLikeCount(suggestId))
        );
    }

//    하트 체크
    @Override
    public Boolean heartCheck(Long memberId, Long suggestId) {
        Long member = suggestLikeRepository.findMemberBySuggestLike(suggestId, memberId);
        return member == 0;
    }

}

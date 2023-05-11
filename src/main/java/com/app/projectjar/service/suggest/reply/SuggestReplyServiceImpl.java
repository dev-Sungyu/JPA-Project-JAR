package com.app.projectjar.service.suggest.reply;

import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.domain.reply.ReplyRequestDTO;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("suggestReply") @Primary
@Slf4j
public class SuggestReplyServiceImpl implements SuggestReplyService {
    private final SuggestReplyRepository suggestReplyRepository;

    private final MemberRepository memberRepository;

    private final SuggestRepository suggestRepository;

    @Override
    public void insertReply(ReplyRequestDTO replyRequestDTO) {
        memberRepository.findById(replyRequestDTO.getMemberId()).ifPresent(
                member ->
                        suggestRepository.findById(replyRequestDTO.getBoardId()).ifPresent(
                                suggest -> {
                                    SuggestReply suggestReply = SuggestReply.builder()
                                            .suggest(suggest)
                                            .member(member)
                                            .replyContent(replyRequestDTO.getReplyContent())
                                            .build();
                                    suggestReplyRepository.save(suggestReply);
                                    suggest.setSuggestReplyCount(suggestReplyRepository.getReplyCount(replyRequestDTO.getBoardId()).intValue());
                                    suggestRepository.save(suggest);
                                }
                        )
        );
    }

    @Override
    public void modifyReply(Long replyId, String replyContent) {
        suggestReplyRepository.findById(replyId).ifPresent(
                suggestReply -> suggestReply.setSuggestReplyContent(replyContent)
        );
    }

    @Override
    public void deleteReply(Long replyId) {
        suggestReplyRepository.findById(replyId).ifPresent(
                suggestReply -> {
                    suggestReplyRepository.delete(suggestReply);
                    suggestRepository.findById(suggestReply.getSuggest().getId()).ifPresent(
                            suggest -> {
                                suggest.setSuggestReplyCount(suggestReplyRepository.getReplyCount(replyId).intValue());
                                suggestRepository.save(suggest);
                            }
                    );
                }
        );
    }

    @Override
    public Slice<ReplyDTO> getReplyList(Long suggestId, Pageable pageable) {
        Slice<SuggestReply> suggestReplyList = suggestReplyRepository.findAllBySuggestWithPaging(suggestId, pageable);

        List<ReplyDTO> replyDTOS = suggestReplyList.getContent().stream().map(this::toReplyDTO).collect(Collectors.toList());
        return new SliceImpl<>(replyDTOS, pageable, suggestReplyList.hasNext());
    }
}

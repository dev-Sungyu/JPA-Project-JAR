package com.app.projectjar.service.suggest.reply;

import com.app.projectjar.domain.ReplyDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import com.app.projectjar.repository.suggest.SuggestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("suggestReply") @Primary
public class SuggestReplyServiceImpl implements SuggestReplyService {
    private final SuggestReplyRepository suggestReplyRepository;

    private final MemberRepository memberRepository;

    private final SuggestRepository suggestRepository;

    @Override
    public void insertReply(Long memberId, Long suggestId, String replyContent) {
        memberRepository.findById(memberId).ifPresent(
                member ->
                        suggestRepository.findById(suggestId).ifPresent(
                                suggest -> {
                                    SuggestReply suggestReply = SuggestReply.builder()
                                            .suggest(suggest)
                                            .member(member)
                                            .replyContent(replyContent)
                                            .build();
                                    suggestReplyRepository.save(suggestReply);
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
                suggestReply -> suggestReplyRepository.delete(suggestReply)
        );
    }

    @Override
    public Slice<ReplyDTO> getReplyList(Long suggestId, Pageable pageable) {
        Slice<SuggestReply> suggestReplyList = suggestReplyRepository.findAllBySuggestWithPaging(suggestId, pageable);
        return null;
    }
}

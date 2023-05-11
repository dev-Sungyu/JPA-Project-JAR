package com.app.projectjar.service.suggest.reply;

import com.app.projectjar.domain.ReplyDTO;
import com.app.projectjar.repository.suggest.SuggestReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("suggestReply") @Primary
public class SuggestReplyServiceImpl implements SuggestReplyService {
    private final SuggestReplyRepository suggestReplyRepository;

    @Override
    public void insertReply(ReplyDTO replyDTO) {

    }

    @Override
    public void modifyReply(Long replyId) {

    }

    @Override
    public void deleteReply(Long replyId) {

    }

    @Override
    public Slice<ReplyDTO> getReplyList(Long suggestId, Pageable pageable) {
        return null;
    }
}

package com.app.projectjar.service.suggest.reply;

import com.app.projectjar.domain.ReplyDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SuggestReplyService {

    // 댓글 저장
    public void insertReply(ReplyDTO replyDTO);
    // 댓글 수정
    public void modifyReply(Long replyId);
    // 댓글 삭제
    public void deleteReply(Long replyId);
    // 댓글 목록
    public Slice<ReplyDTO> getReplyList(Long suggestId, Pageable pageable);

}

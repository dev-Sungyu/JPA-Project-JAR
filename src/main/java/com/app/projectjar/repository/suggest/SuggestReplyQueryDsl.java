package com.app.projectjar.repository.suggest;

import com.app.projectjar.domain.dto.ReplyDTO;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuggestReplyQueryDsl {

    // 댓글 저장 save
    // 댓글 수정 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 댓글 삭제 memberId (화면 쪽에서 검사하기 때문에 따로 만들 필요 x)
    // 전체 조회 ( 페이징 )
    public Page<ReplyDTO> findAllBySuggestWithPaging(Long suggestId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount(Long suggestId);
    // 게시판 정보

}

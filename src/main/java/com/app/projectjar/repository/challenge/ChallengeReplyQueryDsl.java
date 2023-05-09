package com.app.projectjar.repository.challenge;

import com.app.projectjar.domain.ReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChallengeReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Page<ReplyDTO> findAllByChallengeWithPaging(Long challengeId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount(Long challengeId);
}

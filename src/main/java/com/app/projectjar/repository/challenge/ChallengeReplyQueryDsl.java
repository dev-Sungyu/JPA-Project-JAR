package com.app.projectjar.repository.challenge;

public interface ChallengeReplyQueryDsl {

    // 전체 조회 ( 페이징 )
//    public Page<ReplyDTO> findAllByChallengeWithPaging(Long challengeId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount(Long challengeId);
}

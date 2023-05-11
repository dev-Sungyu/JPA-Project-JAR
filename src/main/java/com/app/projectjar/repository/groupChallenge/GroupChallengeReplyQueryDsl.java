package com.app.projectjar.repository.groupChallenge;

public interface GroupChallengeReplyQueryDsl {

    // 전체 조회 ( 페이징 )
//    public Page<ReplyDTO> findAllByGroupChallengeReplyWithPaging(Long groupChallengeId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount(Long groupChallengeId);
}

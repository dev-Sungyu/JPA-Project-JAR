package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.ChallengeAttend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChallengeAttendQueryDsl {


    /* 개인 챌린지 */

    //    내가 완료한 개인 챌린지 전체 조회 (완료) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건 / 참여한 인원 수 + 댓글 개수) + 페이징 처리
    public Page<ChallengeAttend> findAllWithPageAndChallenges_QueryDsl(Long memberId, Pageable pageable);

    //    내가 완료한 개인 챌린지 전체 조회 (종료된 챌린지)
    public Page<ChallengeAttend> findAllWithPageAndEndChallenges_QueryDsl(Long memberId, Pageable pageable);

    public Long getChallengeReplyCount_QueryDsl(Long challengeId);
}

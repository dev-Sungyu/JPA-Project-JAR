package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupChallengeAttendQueryDsl {

    /* 그룹 챌린지 */

    //    내가 완료 중인 그룹 챌린지 전체 조회 (진행 중) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건) + 페이징 처리
    public Page<GroupChallengeAttend> findAllWithPageAndGroupChallenges_QueryDsl(Long memberId, Pageable pageable);

    //    내가 완료 중인 그룹 챌린지 전체 조회 (종료된)
    public Page<GroupChallengeAttend> findAllWithPageAndEndGroupChallenges_QueryDsl(Long memberId, Pageable pageable);

    //    내가 완료한 그룹 챌린지 전체 조회 (종료)
    public Long getGroupChallengeReplyCount_QueryDsl(Long challengeId);

}

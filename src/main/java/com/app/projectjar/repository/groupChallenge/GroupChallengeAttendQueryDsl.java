package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupChallengeAttendQueryDsl {

    // 챌린지 참여 인원 가져오기
    public Integer getAttendCountByGroupChallengeId_QueryDsl(Long groupChallengeId);

    // 해당 챌린지에 참여햇는지 여부 검사
    public Long findByChallengeIdAndMemberId_QueryDsl(Long groupChallengeId, Long memberId);

    // 참여 취소
    public void deleteByGroupChallengeIdAndMemberId_QueryDsl(Long groupChallengeId, Long memberId);

    // 챌린지 참여 번호 가져오기
    public GroupChallengeAttend findGroupChallengeAttendByGroupChallengeIdAndMemberId_QueryDsl(Long groupChallengeId, Long memberId);

    /* 그룹 챌린지 */

    //    내가 완료 중인 그룹 챌린지 전체 조회 (진행 중) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건) + 페이징 처리
    public Page<GroupChallengeAttend> findAllWithPageAndGroupChallenges_QueryDsl(Long memberId, Pageable pageable);

    //    내가 완료 중인 그룹 챌린지 전체 조회 (종료된)
    public Page<GroupChallengeAttend> findAllWithPageAndEndGroupChallenges_QueryDsl(Long memberId, Pageable pageable);

    //    내가 완료한 그룹 챌린지 전체 조회 (종료)
    public Long getGroupChallengeReplyCount_QueryDsl(Long challengeId);

    public void deleteByGroupChallengeId(Long groupChallengeId);

}

package com.app.projectjar.repository.member;

import com.app.projectjar.entity.challenge.ChallengeAttend;
import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BadgeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberQueryDsl {

    /* 로그인 */

//    이메일 중복 검사
    public Optional<Member> overlapByMemberEmail_QueryDSL(String memberEmail);

//    휴대폰 중복 검사
    public Optional<Member> overlapByPhoneNumber_QueryDSL(String memberPhoneNumber);

//    로그인
    public Member findByMemberIdAndMemberPassword_QueryDSL(String memberEmail, String memberPassword);

//    비밀 번호 찾기
    public Optional<Member> findByMemberEmailForPassword_QueryDSL(String memberEmail);

//    비밀 번호 변경
    public void updatePassword_QueryDSL(Long id, String memberPassword);

//    회원 멤버 조회
    public Optional<Member> findByMemberId_QueryDSL(Long id);

//    이메일로 회원 조회
    public Optional<Member> findByMemberEmail_QueryDSL(String memberEmail);

    /* 마이 페이지 */

//    회원 정보 수정
    public void updateMember_QueryDSL(Member memberInfo);

//    회원 삭제
    public void deleteMemberById_QueryDSL(Long id);

    /* 다이어리 */

    /* 뱃지 */

//    챌린지 횟수 조회
    public int findByIdWithAttendCount_QueryDsl(Long id);

//    뱃지 업데이트 ( 개인 챌린지 어탠드 카운트 + 그룹 챌린지 어탠드 카운트 )  => 그러면 attend.member로 접근해서 카운트 세야되는건가?
    public void updateMemberBadge_QueryDSL(Long id, BadgeType badgeType);


    /* 개인 챌린지 */

//    내가 완료한 개인 챌린지 전체 조회 (완료) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건 / 참여한 인원 수 + 댓글 개수) + 페이징 처리
    public Page<ChallengeAttend> findAllWithPageAndChallenges_QueryDsl(Long memberId, Pageable pageable);

//    내가 완료한 개인 챌린지 전체 조회 (종료된 챌린지)
    public Page<ChallengeAttend> findAllWithPageAndEndChallenges_QueryDsl(Long memberId, Pageable pageable);

    public Long getChallengeReplyCount_QueryDsl(Long challengeId);


    /* 그룹 챌린지 */

//    내가 완료 중인 그룹 챌린지 전체 조회 (진행 중) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건) + 페이징 처리
    public Page<GroupChallengeAttend> findAllWithPageAndGroupChallenges_QueryDsl(Long memberId, Pageable pageable);

//    내가 완료 중인 그룹 챌린지 전체 조회 (종료된)
    public Page<GroupChallengeAttend> findAllWithPageAndEndGroupChallenges_QueryDsl(Long memberId, Pageable pageable);

//    내가 완료한 그룹 챌린지 전체 조회 (종료)
    public Long getGroupChallengeReplyCount_QueryDsl(Long challengeId);


    //    관리자 페이지 회원 전체 조회
    public Page<Member> findAllByMemberId_QueryDsl(Pageable pageable);
//  회원 정보 수정
    public void updateMemberAdmin_QueryDSL(Member memberInfo);
}

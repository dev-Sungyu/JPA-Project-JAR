package com.app.projectjar.repository.member;

import com.app.projectjar.domain.dto.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BadgeType;

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

//    회원 멤버 디티오 정보 조회
    public Optional<MemberDTO> findByMemberDTOId_QueryDSL(Long id);

//    회원 멤버 조회
    public Optional<Member> findByMemberId_QueryDSL(Long id);

//    선규 멤버 조회
//    public Optional<Member> findByMemberId_QueryDsl2(Long id);

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

//    내가 완료한 개인 챌린지 전체 조회 (진행 중) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건 / 참여한 인원 수 + 댓글 개수) + 페이징 처리
//    public page<Challenge> findByAllWithPage(Pageable pageable);


//    내가 완료한 개인 챌린지 전체 조회 (종료)


    /* 그룹 챌린지 */

//    내가 완료 중인 그룹 챌린지 전체 조회 (진행 중) (challengeAttend -> memberId 조회 후 참여한 목록 + 날짜 조건) + 페이징 처리


//    내가 완료한 그룹 챌린지 전체 조회 (종료)


    /* 챌린지 제안 */

//  제안 게시물 전체 조회 (제안 게시물에서 memberid 조회 후 list로 가져와 뿌리기)

//   제안 게시물 수정 누르면 작성하기 쪽으로 넘겨야 되는거 물어보자.

//   제안 게시물 삭제



    /* 공유 일기 */

//    공유 게시물 전체 조회

//    공유 게시물 수정

//    공유 게시물 삭제


    /* 내가 좋아요한 게시물  */

//    좋아요한 게시물 전체 조회   ( Like 테이블에서 memberId 조회 후 게시물 접근해서 리스트로 )

//    좋아요 삭제


    /* 문의 글 */

//    문의 게시글 전체 조회 (문의 게시글 답변 체크 후 스테이스 변경)

//    문의 게시글 수정

//    문의 게시글 삭제


}

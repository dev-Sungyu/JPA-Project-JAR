package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.domain.dto.MemberDTO;
import com.app.projectjar.type.BadgeType;

import java.util.Optional;

public interface MemberQueryDsl {

    /* 로그인 */

//    이메일 중복 검사
    public Optional<Member> overlapByMemberEmail(String memberEmail);

//    휴대폰 중복 검사
    public Optional<Member> overlapByPhoneNumber(String memberPhoneNumber);

//    로그인
    public Member findByMemberIdAndMemberPassword(String memberEmail, String memberPassword);

//    비밀 번호 변경
    public void updatePassword(Long id, String memberPassword);

//    회원 멤버 디티오 정보 조회
    public Optional<MemberDTO> findByMemberDTOId(Long id);

//    회원 멤버 조회
    public Optional<Member> findByMemberId(Long id);

    /* 마이 페이지 */

//    회원 정보 수정
    public void updateMember(Member memberInfo);

//    회원 삭제
    public void deleteMemberById(Long id);

//    뱃지 업데이트
    public void updateMemberBadge(Long id, BadgeType badgeType);

//
}

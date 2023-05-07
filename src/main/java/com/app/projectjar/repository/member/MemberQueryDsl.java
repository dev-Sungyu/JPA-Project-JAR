package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.MemberDTO;
import com.app.projectjar.type.BadgeType;

import java.util.Optional;

public interface MemberQueryDsl {

    /* 로그인 */

//    이메일 중복 검사
    public Optional<Member> findByMemberEmail(String memberEmail);

//    휴대폰 중복 검사
    public Optional<Member> findByPhoneNumber(String memberPhoneNumber);

//    로그인
    public Member findByMemberIdAndMemberPassword(String memberEmail, String memberPassword);

//    비밀 번호 변경
    public void updatePassword(String memberPassword);

//    회원 정보 조회
    public Optional<Member> findByMemberId(Long memberId);

    /* 마이 페이지 */

//    회원 정보 수정
    public MemberDTO updateMember(MemberDTO memberDTO);

//    뱃지 업데이트
    public Member updateMemberBadge(Long memberId, BadgeType badgeType);
}

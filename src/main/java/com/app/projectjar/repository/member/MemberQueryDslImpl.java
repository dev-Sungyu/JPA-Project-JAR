package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.MemberDTO;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.type.BadgeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.app.projectjar.entity.member.QMember.member;

public class MemberQueryDslImpl implements MemberQueryDsl {
    private JPAQueryFactory query;

//    이메일 중복 검사
    @Override
    public Optional<Member> findByMemberEmail(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

//    휴대폰 중복 검사
    @Override
    public Optional<Member> findByPhoneNumber(String memberPhoneNumber) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberPhoneNumber.eq(memberPhoneNumber)).fetchOne());
    }

//    로그인
    @Override
    public Member findByMemberIdAndMemberPassword(String memberEmail, String memberPassword) {
        return query.select(member).from(member).where(member.memberEmail.eq(memberEmail),member.memberPassword.eq(memberPassword)).fetchOne();
    }

//    비밀 번호 변경
    @Override
    public void updatePassword(String memberPassword) {
//        query.update(member).set(member.memberPassword).where(member.mem)
    }

//    회원 정보 조회
    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return Optional.ofNullable(query.select(member).from(member).where(member.id.eq(memberId)).fetchOne());
//        return Optional.ofNullable(query.select(member).from(member).join(member.memberFile).fetchJoin().where(member.id.eq(memberId)).fetchOne())
    }

//    회원정보 수정
    @Override
    public MemberDTO updateMember(MemberDTO memberDTO) {
        return null;
    }

    @Override
    public Member updateMemberBadge(Long memberId, BadgeType badgeType) {
        return null;
//                query.update(member).set(member.bedgeType.)
    }


}

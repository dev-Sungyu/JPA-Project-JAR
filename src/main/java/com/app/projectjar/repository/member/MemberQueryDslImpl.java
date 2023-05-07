package com.app.projectjar.repository.member;

import com.app.projectjar.domain.dto.QMemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.domain.dto.MemberDTO;
import com.app.projectjar.type.BadgeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberQueryDslImpl implements MemberQueryDsl {
    private final JPAQueryFactory query;

//    이메일 중복 검사
    @Override
    public Optional<Member> overlapByMemberEmail(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

//    휴대폰 중복 검사
    @Override
    public Optional<Member> overlapByPhoneNumber(String memberPhoneNumber) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberPhoneNumber.eq(memberPhoneNumber)).fetchOne());
    }

//    로그인
    @Override
    public Member findByMemberIdAndMemberPassword(String memberEmail, String memberPassword) {
        return query.select(member).from(member).where(member.memberEmail.eq(memberEmail),member.memberPassword.eq(memberPassword)).fetchOne();
    }

//    비밀 번호 변경
    @Override
    public void updatePassword(Long id, String memberPassword) {
        query.update(member).set(member.memberPassword, memberPassword).where(member.id.eq(id)).execute();
    }

//    멤버 디티오 정보 조회
    @Override
    public Optional<MemberDTO> findByMemberDTOId(Long id) {
        return Optional.ofNullable(
                query.select(new QMemberDTO(
                        member.id,
                        member.memberEmail,
                        member.memberPassword,
                        member.memberPhoneNumber,
                        member.memberName,
                        member.memberNickname,
                        member.memberStatus,
                        member.badgeType
                ))
                        .from(member)
                        .leftJoin(member.memberFile, memberFile)
                        .where(member.id.eq(id))
                        .fetchOne());
//        return Optional.ofNullable(query.select(member).from(member).join(member.memberFile).fetchJoin().where(member.id.eq(memberId)).fetchOne())
    }

//    멤버 정보 조회
    @Override
    public Optional<Member> findByMemberId(Long id) {
        return Optional.ofNullable(query.select(member).
                from(member).
                where(member.id.eq(id)).fetchOne());
    }

//    회원 정보 수정
    @Override
    public void updateMember(Member memberInfo) {
                query.update(member).
                set(member.memberName, memberInfo.getMemberName()).
                set(member.memberNickname, memberInfo.getMemberNickname()).
                set(member.memberPhoneNumber, memberInfo.getMemberPhoneNumber()).
                where(member.eq(memberInfo)).execute();
    }

//    회원 삭제
    @Override
    public void deleteMemberById(Long id) {
            query.delete(member).where(member.id.eq(id)).execute();

    }

//    뱃지 수정
    @Override
    public void updateMemberBadge(Long id, BadgeType badgeType) {
    }

}

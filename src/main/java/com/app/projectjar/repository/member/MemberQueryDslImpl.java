package com.app.projectjar.repository.member;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.type.BadgeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.app.projectjar.entity.challenge.QChallengeAttend.challengeAttend;
import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallengeAttend.groupChallengeAttend;
import static com.app.projectjar.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberQueryDslImpl implements MemberQueryDsl {
    private final JPAQueryFactory query;

//    이메일 중복 검사
    @Override
    public Optional<Member> overlapByMemberEmail_QueryDSL(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

//    휴대폰 중복 검사
    @Override
    public Optional<Member> overlapByPhoneNumber_QueryDSL(String memberPhoneNumber) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberPhoneNumber.eq(memberPhoneNumber)).fetchOne());
    }

//    로그인
    @Override
    public Member findByMemberIdAndMemberPassword_QueryDSL(String memberEmail, String memberPassword) {
        return query.select(member).from(member).where(member.memberEmail.eq(memberEmail),member.memberPassword.eq(memberPassword)).fetchOne();
    }

//    비밀 번호 찾기
    @Override
    public Optional<Member> findByMemberEmailForPassword_QueryDSL(String memberEmail) {
        return Optional.ofNullable(query.select(member).from(member).where(member.memberEmail.eq(memberEmail)).fetchOne());
    }

//    비밀 번호 변경
    @Override
    public void updatePassword_QueryDSL(Long id, String memberPassword) {
        query.update(member).set(member.memberPassword, memberPassword).where(member.id.eq(id)).execute();
    }

//    멤버 정보 조회
    @Override
    public Optional<Member> findByMemberId_QueryDSL(Long id) {
        Member member = query.select(QMember.member)
                .from(QMember.member)
                .leftJoin(QMember.member.memberFile, memberFile)
                .fetchJoin()
                .where(QMember.member.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(member);
    }

//    회원 정보 수정
    @Override
    public void updateMember_QueryDSL(Member memberInfo) {
                query.update(member).
                set(member.memberName, memberInfo.getMemberName()).
                set(member.memberNickname, memberInfo.getMemberNickname()).
                set(member.memberPhoneNumber, memberInfo.getMemberPhoneNumber()).
                where(member.eq(memberInfo)).execute();
    }

//    회원 삭제
    @Override
    public void deleteMemberById_QueryDSL(Long id) {
            query.delete(member).where(member.id.eq(id)).execute();

    }

//    챌린지 횟수 조회
    @Override
    public int findByIdWithAttendCount_QueryDsl(Long id) {
        Long countPersonal = query.select(challengeAttend.member.count())
                .from(challengeAttend).
                where(challengeAttend.member.id.eq(id)).
                fetchOne();

        Long countGroup = query.select(groupChallengeAttend.member.count()).
                from(groupChallengeAttend).
                where(groupChallengeAttend.member.id.eq(id)).
                fetchOne();

        int totalCount = countPersonal.intValue() + countGroup.intValue();

        return totalCount;


    }

//    뱃지 수정
    @Override
    public void updateMemberBadge_QueryDSL(Long id, BadgeType badgeType) {

        Long countPersonal =  query.select(challengeAttend.member.count()).
                from(challengeAttend).
                where(challengeAttend.member.id.eq(id)).
                fetchOne();

        Long countGroup = query.select(groupChallengeAttend.member.count()).
                from(groupChallengeAttend).
                where(groupChallengeAttend.member.id.eq(id)).
                fetchOne();

        int totalCount = countPersonal.intValue() + countGroup.intValue();

//        BadgeType newBadgeType = badgeType;
//
//        if (totalCount >= 10) {
//            newBadgeType = BadgeType.ONE;
//
//            if (totalCount >= 20) {
//                newBadgeType = BadgeType.TWO;
//            }
//            if (totalCount >= 30) {
//                newBadgeType = BadgeType.THREE;
//            }
//        } else {
//            newBadgeType = BadgeType.ZERO;
//        }
//        if (newBadgeType != badgeType) {

//        연산은 서비스에서 진행하기
            query.update(member)
                    .set(member.badgeType, badgeType)
                    .where(member.id.eq(id))
                    .execute();
    }

}

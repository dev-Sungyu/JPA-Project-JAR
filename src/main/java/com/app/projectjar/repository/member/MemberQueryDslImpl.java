package com.app.projectjar.repository.member;

import com.app.projectjar.entity.challenge.ChallengeAttend;
import com.app.projectjar.entity.challenge.QChallengeReply;
import com.app.projectjar.entity.file.challenge.QChallengeFile;
import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.entity.groupChallenge.QGroupChallengeReply;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.ChallengeType;
import com.app.projectjar.type.GroupChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.challenge.QChallengeAttend.challengeAttend;
import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;
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
        query.update(member).set(member.memberPassword, memberPassword).where(member.memberId.eq(id)).execute();
    }

//    멤버 정보 조회
    @Override
    public Optional<Member> findByMemberId_QueryDSL(Long id) {
        Member member = query.select(QMember.member)
                .from(QMember.member)
                .leftJoin(QMember.member.memberFile, memberFile)
                .fetchJoin()
                .where(QMember.member.memberId.eq(id))
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
            query.delete(member).where(member.memberId.eq(id)).execute();

    }

//    챌린지 횟수 조회
    @Override
    public int findByIdWithAttendCount_QueryDsl(Long id) {
        Long countPersonal = query.select(challengeAttend.member.count())
                .from(challengeAttend).
                where(challengeAttend.member.memberId.eq(id)).
                fetchOne();

        Long countGroup = query.select(groupChallengeAttend.member.count()).
                from(groupChallengeAttend).
                where(groupChallengeAttend.member.memberId.eq(id)).
                fetchOne();

        int totalCount = countPersonal.intValue() + countGroup.intValue();

        return totalCount;


    }

//    개인 챌린지 목록(페이징, 진행중)

    @Override
    public Page<ChallengeAttend> findAllWithPageAndChallenges_QueryDsl(Long memberId, Pageable pageable) {
        List<ChallengeAttend> foundChallengeAttend = query.selectFrom(challengeAttend)
                .join(challengeAttend.member, member)
                .leftJoin(challengeAttend.challenge, challenge)
                .where(challengeAttend.member.memberId.eq(memberId))
                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("OPEN")))
                .leftJoin(challenge.challengeFiles, QChallengeFile.challengeFile)
                .orderBy(challengeAttend.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(challengeAttend.count())
                .from(challengeAttend)
                .join(challengeAttend.member, member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundChallengeAttend, pageable, count);
    }

//    내가 완료한 개인 챌린지 전체 조회 (종료된 챌린지)
    @Override
    public Page<ChallengeAttend> findAllWithPageAndEndChallenges_QueryDsl(Long memberId, Pageable pageable) {
        List<ChallengeAttend> foundChallengeAttend = query.selectFrom(challengeAttend)
                .join(challengeAttend.member, member)
                .leftJoin(challengeAttend.challenge, challenge)
                .where(challengeAttend.member.memberId.eq(memberId))
                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("PRIVATE")))
                .leftJoin(challenge.challengeFiles, QChallengeFile.challengeFile)
                .orderBy(challengeAttend.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(challengeAttend.count())
                .from(challengeAttend)
                .join(challengeAttend.member, member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundChallengeAttend, pageable, count);
    }

//    개인 챌린지 목록(댓글 갯수)
    @Override
    public Long getChallengeReplyCount_QueryDsl(Long challengeId) {
        QChallengeReply challengeReply = QChallengeReply.challengeReply;

        return query.select(challengeReply.count())
                .from(challengeReply)
                .where(challengeReply.challenge.id.eq(challengeId))
                .fetchOne();
}
//    그룹 챌린지 목록(페이징, 진행중)

    @Override
    public Page<GroupChallengeAttend> findAllWithPageAndGroupChallenges_QueryDsl(Long memberId, Pageable pageable) {

        List<GroupChallengeAttend> foundGroupChallengeAttend = query.selectFrom(groupChallengeAttend)
                .join(groupChallengeAttend.member, member)
                .leftJoin(groupChallengeAttend.groupChallenge, groupChallenge)
                .where(groupChallengeAttend.member.memberId.eq(memberId))
                .where(groupChallenge.groupChallengeStatus.eq(GroupChallengeType.valueOf("OPEN")))
                .where(groupChallenge.startDate.before(LocalDate.now()))
                .where(groupChallenge.endDate.after(LocalDate.now()))
                .orderBy(groupChallengeAttend.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(groupChallengeAttend.count())
                .from(groupChallengeAttend)
                .join(groupChallengeAttend.member, member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundGroupChallengeAttend, pageable, count);
    }

//    그룹 챌린지 목록(페이징, 종료된)
    @Override
    public Page<GroupChallengeAttend> findAllWithPageAndEndGroupChallenges_QueryDsl(Long memberId, Pageable pageable) {

        List<GroupChallengeAttend> foundGroupChallengeAttend = query.selectFrom(groupChallengeAttend)
                .join(groupChallengeAttend.member, member)
                .leftJoin(groupChallengeAttend.groupChallenge, groupChallenge)
                .where(groupChallengeAttend.member.memberId.eq(memberId))
                .where(groupChallenge.groupChallengeStatus.eq(GroupChallengeType.valueOf("PRIVATE")))
                .where(groupChallenge.startDate.before(LocalDate.now()))
                .where(groupChallenge.endDate.after(LocalDate.now()))
                .orderBy(groupChallengeAttend.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(groupChallengeAttend.count())
                .from(groupChallengeAttend)
                .join(groupChallengeAttend.member, member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundGroupChallengeAttend, pageable, count);
    }

//    그룹 챌린지 목록(댓글 갯수)
    @Override
    public Long getGroupChallengeReplyCount_QueryDsl(Long groupChallengeId) {
        QGroupChallengeReply groupChallengeReply = QGroupChallengeReply.groupChallengeReply;

        return query.select(groupChallengeReply.count())
                .from(groupChallengeReply)
                .where(groupChallengeReply.groupChallenge.id.eq(groupChallengeId))
                .fetchOne();
}





    //    뱃지 수정
    @Override
    public void updateMemberBadge_QueryDSL(Long id, BadgeType badgeType) {

        Long countPersonal =  query.select(challengeAttend.member.count()).
                from(challengeAttend).
                where(challengeAttend.member.memberId.eq(id)).
                fetchOne();

        Long countGroup = query.select(groupChallengeAttend.member.count()).
                from(groupChallengeAttend).
                where(groupChallengeAttend.member.memberId.eq(id)).
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
                    .where(member.memberId.eq(id))
                    .execute();
    }


    //------------------------------------------ 관리자 페이지 -----------------------------------------------------
    //    관리자 페이지 회원 전체 조회
    @Override
    public Page<Member> findAllByMemberId_QueryDsl(Pageable pageable) {
        List<Member> foundMembers = query.select(QMember.member)
                .from(member)
                .leftJoin(member.memberFile, memberFile)
                .fetchJoin()
                .orderBy(member.memberId.desc())
                .offset(pageable.getOffset() -1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(member.count())
                .from(member)
                .fetchOne();

        return new PageImpl<>(foundMembers, pageable ,count);
    }

    //    회원 정보 수정
    @Override
    public void updateMemberAdmin_QueryDSL(Member memberInfo) {
        query.update(member).
                set(member.memberEmail, memberInfo.getMemberEmail()).
                set(member.memberNickname, memberInfo.getMemberNickname()).
                set(member.memberPhoneNumber, memberInfo.getMemberPhoneNumber()).
                set(member.memberStatus, memberInfo.getMemberStatus()).
                where(member.eq(memberInfo)).execute();
    }

}

package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.entity.groupChallenge.GroupChallengeAttend;
import com.app.projectjar.entity.groupChallenge.QGroupChallengeReply;
import com.app.projectjar.type.GroupChallengeAttendType;
import com.app.projectjar.type.GroupChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile.groupChallengeFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;
import static com.app.projectjar.entity.groupChallenge.QGroupChallengeAttend.groupChallengeAttend;
import static com.app.projectjar.entity.member.QMember.member;

@RequiredArgsConstructor
public class GroupChallengeAttendQueryDslImpl implements GroupChallengeAttendQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Integer getAttendCountByGroupChallengeId_QueryDsl(Long groupChallengeId) {
        return query.select(groupChallengeAttend.count())
                .from(groupChallengeAttend)
                .where(groupChallengeAttend.groupChallenge.id.eq(groupChallengeId))
                .fetchOne().intValue();
    }

    @Override
    public Long findByChallengeIdAndMemberId_QueryDsl(Long groupChallengeId, Long memberId) {
        return query.select(groupChallengeAttend.count())
                .from(groupChallengeAttend)
                .where(groupChallengeAttend.groupChallenge.id.eq(groupChallengeId).and(groupChallengeAttend.member.id.eq(memberId)))
                .fetchOne();
    }

    @Override
    public void deleteByGroupChallengeIdAndMemberId_QueryDsl(Long groupChallengeId, Long memberId) {
        query.delete(groupChallengeAttend)
                .where(groupChallengeAttend.groupChallenge.id.eq(groupChallengeId).and(groupChallengeAttend.member.id.eq(memberId)))
                .execute();
    }

    @Override
    public GroupChallengeAttend findGroupChallengeAttendByGroupChallengeIdAndMemberId_QueryDsl(Long groupChallengeId, Long memberId) {
        return query.select(groupChallengeAttend)
                .from(groupChallengeAttend)
                .where(groupChallengeAttend.groupChallenge.id.eq(groupChallengeId).and(groupChallengeAttend.member.id.eq(memberId)))
                .fetchOne();
    }

    @Override
    public Integer getCountByMemberId_QueryDsl(Long memberId) {
        return query.select(groupChallengeAttend.count())
                .from(groupChallengeAttend)
                .where(groupChallengeAttend.groupChallengeAttendStatus.eq(GroupChallengeAttendType.PARTICIPATION))
                .where(groupChallengeAttend.member.id.eq(memberId))
                .fetchOne().intValue();
    }

    //    그룹 챌린지 목록(페이징, 진행중)
    @Override
    public Page<GroupChallengeAttend> findAllWithPageAndGroupChallenges_QueryDsl(Long memberId, Pageable pageable) {

        List<GroupChallengeAttend> foundGroupChallengeAttend = query.selectFrom(groupChallengeAttend)
                .join(groupChallengeAttend.member, member)
                .leftJoin(groupChallengeAttend.groupChallenge, groupChallenge)
                .where(groupChallengeAttend.member.id.eq(memberId))
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
                .where(member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundGroupChallengeAttend, pageable, count);
    }

    //    그룹 챌린지 목록(페이징, 종료된)
    @Override
    public Page<GroupChallengeAttend> findAllWithPageAndEndGroupChallenges_QueryDsl(Long memberId, Pageable pageable) {

        List<GroupChallengeAttend> foundGroupChallengeAttend = query.selectFrom(groupChallengeAttend)
                .join(groupChallengeAttend.member, member)
                .leftJoin(groupChallengeAttend.groupChallenge, groupChallenge)
                .where(groupChallengeAttend.member.id.eq(memberId))
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
                .where(member.id.eq(memberId))
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


    @Override
    @Transactional
    public void deleteByGroupChallengeId(Long groupChallengeId) {
        query.delete(groupChallengeAttend)
                .where(groupChallengeAttend.groupChallenge.id.eq(groupChallengeId))
                .execute();
    }

}

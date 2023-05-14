package com.app.projectjar.repository.challenge;


import com.app.projectjar.entity.challenge.ChallengeAttend;
import com.app.projectjar.entity.challenge.QChallengeReply;
import com.app.projectjar.entity.file.challenge.QChallengeFile;
import com.app.projectjar.type.ChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.challenge.QChallengeAttend.challengeAttend;
import static com.app.projectjar.entity.member.QMember.member;

@RequiredArgsConstructor
public class ChallengeAttendQueryDslImpl implements ChallengeAttendQueryDsl {
    private final JPAQueryFactory query;
//    개인 챌린지 목록(페이징, 진행중)

    @Override
    public Page<ChallengeAttend> findAllWithPageAndChallenges_QueryDsl(Long memberId, Pageable pageable) {
        List<ChallengeAttend> foundChallengeAttend = query.selectFrom(challengeAttend)
                .join(challengeAttend.member, member)
                .leftJoin(challengeAttend.challenge, challenge)
                .where(challengeAttend.member.id.eq(memberId))
                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("OPEN")))
                .leftJoin(challenge.challengeFiles, QChallengeFile.challengeFile)
                .orderBy(challengeAttend.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(challengeAttend.count())
                .from(challengeAttend)
                .join(challengeAttend.member, member)
                .where(member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundChallengeAttend, pageable, count);
    }

    //    내가 완료한 개인 챌린지 전체 조회 (종료된 챌린지)
    @Override
    public Page<ChallengeAttend> findAllWithPageAndEndChallenges_QueryDsl(Long memberId, Pageable pageable) {
        List<ChallengeAttend> foundChallengeAttend = query.selectFrom(challengeAttend)
                .join(challengeAttend.member, member)
                .leftJoin(challengeAttend.challenge, challenge)
                .where(challengeAttend.member.id.eq(memberId))
                .where(challenge.challengeStatus.eq(ChallengeType.valueOf("PRIVATE")))
                .leftJoin(challenge.challengeFiles, QChallengeFile.challengeFile)
                .orderBy(challengeAttend.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(challengeAttend.count())
                .from(challengeAttend)
                .join(challengeAttend.member, member)
                .where(member.id.eq(memberId))
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
}

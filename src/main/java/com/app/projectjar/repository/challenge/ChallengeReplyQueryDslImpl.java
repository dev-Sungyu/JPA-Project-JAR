package com.app.projectjar.repository.challenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.projectjar.entity.challenge.QChallengeReply.challengeReply;

@RequiredArgsConstructor
public class ChallengeReplyQueryDslImpl implements ChallengeReplyQueryDsl {
    private final JPAQueryFactory query;

//    @Override
//    public Page<ReplyDTO> findAllByChallengeWithPaging(Long challengeId, Pageable pageable) {
//        List<ReplyDTO> foundReply = query.select(
//                new QReplyDTO(
//                        challengeReply.id,
//                        challengeReply.replyContent,
//                        challengeReply.updatedDate,
//                        challengeReply.member.id,
//                        challengeReply.member.memberNickname,
//                        challengeReply.member.memberFile.fileOriginalName,
//                        challengeReply.member.memberFile.fileUuid,
//                        challengeReply.member.memberFile.filePath,
//                        challengeReply.member.badgeType
//                ))
//                .from(challengeReply)
//                .leftJoin(challengeReply.member.memberFile)
//                .where(challengeReply.challenge.id.eq(challengeId))
//                .orderBy(challengeReply.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long count = getReplyCount(challengeId);
//
//        return new PageImpl<>(foundReply,pageable,count);
//    }

    @Override
    public Long getReplyCount(Long challengeId) {
        return query.select(challengeReply.count())
                .from(challengeReply)
                .where(challengeReply.id.eq(challengeId))
                .fetchOne();
    }
}

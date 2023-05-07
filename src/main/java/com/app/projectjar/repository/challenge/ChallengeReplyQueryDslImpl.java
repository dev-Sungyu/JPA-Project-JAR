package com.app.projectjar.repository.challenge;


import com.app.projectjar.domain.dto.QReplyDTO;
import com.app.projectjar.domain.dto.ReplyDTO;
import com.app.projectjar.entity.challenge.QChallengeReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.challenge.QChallengeReply.challengeReply;
import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class ChallengeReplyQueryDslImpl implements ChallengeReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<ReplyDTO> findAllByChallengeWithPaging(Long challengeId, Pageable pageable) {
        List<ReplyDTO> foundReply = query.select(
                new QReplyDTO(
                        challengeReply.id,
                        challengeReply.replyContent,
                        challengeReply.updatedDate,
                        challengeReply.member.id,
                        challengeReply.member.memberNickname,
                        challengeReply.member.memberFile.fileOriginalName,
                        challengeReply.member.memberFile.fileUuid,
                        challengeReply.member.memberFile.filePath,
                        challengeReply.member.badgeType
                ))
                .from(challengeReply)
                .leftJoin(challengeReply.member.memberFile)
                .where(challengeReply.challenge.id.eq(challengeId))
                .orderBy(challengeReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = getReplyCount(challengeId);

        return new PageImpl<>(foundReply,pageable,count);
    }

    @Override
    public Long getReplyCount(Long challengeId) {
        return query.select(challengeReply.count())
                .from(challengeReply)
                .where(challengeReply.id.eq(challengeId))
                .fetchOne();
    }
}

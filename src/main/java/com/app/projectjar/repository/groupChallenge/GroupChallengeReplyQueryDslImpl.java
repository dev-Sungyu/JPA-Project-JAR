package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.domain.QReplyDTO;
import com.app.projectjar.domain.ReplyDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallengeReply.groupChallengeReply;

@RequiredArgsConstructor
public class GroupChallengeReplyQueryDslImpl implements GroupChallengeReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<ReplyDTO> findAllByGroupChallengeReplyWithPaging(Long groupChallengeId, Pageable pageable) {
        List<ReplyDTO> foundReply = query.select(
                new QReplyDTO(
                        groupChallengeReply.id,
                        groupChallengeReply.replyContent,
                        groupChallengeReply.updatedDate,
                        groupChallengeReply.member.id,
                        groupChallengeReply.member.memberNickname,
                        groupChallengeReply.member.memberFile.fileOriginalName,
                        groupChallengeReply.member.memberFile.fileUuid,
                        groupChallengeReply.member.memberFile.filePath,
                        groupChallengeReply.member.badgeType
                ))
                .from(groupChallengeReply)
                .leftJoin(groupChallengeReply.member.memberFile, memberFile)
                .where(groupChallengeReply.groupChallenge.id.eq(groupChallengeId))
                .orderBy(groupChallengeReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = getReplyCount(groupChallengeId);

        return new PageImpl<>(foundReply,pageable,count);
    }

    @Override
    public Long getReplyCount(Long groupChallengeId) {
        return query.select(groupChallengeReply.count())
                .from(groupChallengeReply)
                .where(groupChallengeReply.id.eq(groupChallengeId))
                .fetchOne();
    }
}

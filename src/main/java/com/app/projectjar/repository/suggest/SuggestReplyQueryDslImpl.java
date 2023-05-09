package com.app.projectjar.repository.suggest;


import com.app.projectjar.domain.QReplyDTO;
import com.app.projectjar.domain.ReplyDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class SuggestReplyQueryDslImpl implements SuggestReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<ReplyDTO> findAllBySuggestWithPaging(Long suggestId, Pageable pageable) {
        List<ReplyDTO> foundReply = query.select(
                new QReplyDTO(
                        suggestReply.id,
                        suggestReply.replyContent,
                        suggestReply.updatedDate,
                        suggestReply.member.id,
                        suggestReply.member.memberNickname,
                        suggestReply.member.memberFile.fileOriginalName,
                        suggestReply.member.memberFile.fileUuid,
                        suggestReply.member.memberFile.filePath,
                        suggestReply.member.badgeType
                        ))
                .from(suggestReply)
            .leftJoin(suggestReply.member.memberFile, memberFile)
                .where(suggestReply.suggest.id.eq(suggestId))
                .orderBy(suggestReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = getReplyCount(suggestId);

        return new PageImpl<>(foundReply,pageable,count);
    }

    @Override
    public Long getReplyCount(Long suggestId) {
        return query.select(suggestReply.count())
                .from(suggestReply)
                .where(suggestReply.id.eq(suggestId))
                .fetchOne();
    }
}

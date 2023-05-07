package com.app.projectjar.repository.diary;


import com.app.projectjar.domain.dto.QReplyDTO;
import com.app.projectjar.domain.dto.ReplyDTO;
import com.app.projectjar.entity.diary.QDiaryReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.diary.QDiaryReply.diaryReply;
import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class DiaryReplyQueryDslImpl implements DiaryReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<ReplyDTO> findAllByDiaryWithPaging(Long diaryId, Pageable pageable) {
        List<ReplyDTO> foundReply = query.select(
                new QReplyDTO(
                        diaryReply.id,
                        diaryReply.replyContent,
                        diaryReply.updatedDate,
                        diaryReply.member.id,
                        diaryReply.member.memberNickname,
                        diaryReply.member.memberFile.fileOriginalName,
                        diaryReply.member.memberFile.fileUuid,
                        diaryReply.member.memberFile.filePath,
                        diaryReply.member.badgeType
                ))
                .from(diaryReply)
                .leftJoin(diaryReply.member.memberFile, memberFile)
                .where(diaryReply.diary.id.eq(diaryId))
                .orderBy(diaryReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = getReplyCount(diaryId);

        return new PageImpl<>(foundReply,pageable,count);
    }

    @Override
    public Long getReplyCount(Long diaryId) {
        return query.select(diaryReply.count())
                .from(diaryReply)
                .where(diaryReply.id.eq(diaryId))
                .fetchOne();
    }
}

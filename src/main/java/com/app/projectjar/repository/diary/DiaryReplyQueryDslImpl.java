package com.app.projectjar.repository.diary;


import com.app.projectjar.domain.dto.QReplyDTO;
import com.app.projectjar.domain.dto.ReplyDTO;
import com.app.projectjar.entity.diary.DiaryReply;
import com.app.projectjar.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.diary.QDiaryReply.diaryReply;
import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.member.QMember.member;

@RequiredArgsConstructor
public class DiaryReplyQueryDslImpl implements DiaryReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<DiaryReply> findAllByDiaryWithPaging_QueryDsl(Long diaryId, Pageable pageable) {
        List<DiaryReply> foundDiaryReply = query.select(diaryReply)
                .from(diaryReply)
                .leftJoin(diaryReply.member, member)
                .fetchJoin()
                .where(diaryReply.diary.id.eq(diaryId))
                .orderBy(diaryReply.id.desc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = getReplyCount_QueryDsl(diaryId);

        return new PageImpl<>(foundDiaryReply, pageable, count);
    }

    @Override
    public Long getReplyCount_QueryDsl(Long diaryId) {
        return query.select(diaryReply.count())
                .from(diaryReply)
                .where(diaryReply.id.eq(diaryId))
                .fetchOne();
    }
}

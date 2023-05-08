package com.app.projectjar.repository.diary;


import com.app.projectjar.domain.dto.*;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.diary.QDiary;
import com.app.projectjar.entity.file.diary.QDiaryFile;
import com.app.projectjar.entity.file.member.QMemberFile;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.type.DiaryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.diary.QDiary.diary;
import static com.app.projectjar.entity.file.diary.QDiaryFile.diaryFile;
import static com.app.projectjar.entity.file.member.QMemberFile.memberFile;
import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class DiaryQueryDslImpl implements DiaryQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public Optional<Diary> findByDiaryId_QueryDsl(Long diaryId) {
        Diary foundDiary = query.select(diary)
                .from(diary)
                .leftJoin(diary.diaryFiles, diaryFile)
                .fetchJoin()
                .leftJoin(diary.member, member)
                .fetchJoin()
                .where(diary.id.eq(diaryId))
                .fetchOne();

        return Optional.ofNullable(foundDiary);
    }

    @Override
    public Page<Diary> findAllDiary_QueryDsl(Pageable pageable) {
        List<Diary> foundDiaries = query.select(diary)
                .from(diary)
                .leftJoin(diary.diaryFiles, diaryFile)
                .fetchJoin()
                .where(diary.diaryStatus.eq(DiaryType.valueOf("OPEN")))
                .orderBy(diary.id.desc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(diary.count())
                .from(diary)
                .where(diary.diaryStatus.eq(DiaryType.valueOf("OPEN")))
                .fetchOne();

        return new PageImpl<>(foundDiaries, pageable, count);
    }
}

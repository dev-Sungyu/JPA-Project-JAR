package com.app.projectjar.repository.diary;


import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.diary.QDiary;
import com.app.projectjar.entity.diary.QDiaryLike;
import com.app.projectjar.entity.file.diary.QDiaryFile;
import com.app.projectjar.entity.file.member.QMemberFile;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.type.DiaryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.diary.QDiary.diary;
import static com.app.projectjar.entity.diary.QDiaryLike.diaryLike;
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
    public Slice<Diary> findAllDiary_QueryDsl(String sort, Pageable pageable) {
            List<Diary> foundDiaryList = query.select(diary)
                    .from(diary)
                    .leftJoin(diary.diaryFiles, diaryFile)
                    .fetchJoin()
                    .orderBy(sort.equals("recent") ? diary.id.desc() : diary.diaryLikeCount.desc())
                    .where(diary.diaryStatus.eq(DiaryType.valueOf("OPEN")))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize() + 1)
                    .fetch();

        boolean hasNext = false;
        if (foundDiaryList.size() > pageable.getPageSize()) {
            foundDiaryList.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(foundDiaryList, pageable, hasNext);
    }

    @Override
    public Page<Diary> findAllByMemberWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Diary> foundDiaries = query.select(diary)
                .from(diary)
                .where(diary.member.id.eq(id))
                .leftJoin(diary.diaryFiles)
                .fetchJoin()
                .orderBy(diary.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(diary.count())
                .from(diary)
                .where(diary.member.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundDiaries, pageable, count);
    }

    @Override
    public Page<Diary> findByLikeMEmberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Diary> foundDiaries = query.select(diary)
                .from(diary)
                .leftJoin(diaryLike)
                .where(diary.member.id.eq(id))
                .orderBy(diary.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(foundDiaries);
    }
}

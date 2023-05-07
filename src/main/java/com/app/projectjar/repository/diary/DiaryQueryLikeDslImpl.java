package com.app.projectjar.repository.diary;


import com.app.projectjar.entity.diary.QDiaryLike;
import com.app.projectjar.entity.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.projectjar.entity.diary.QDiaryLike.diaryLike;
import static com.app.projectjar.entity.suggest.QSuggestLike.suggestLike;

@RequiredArgsConstructor
public class DiaryQueryLikeDslImpl implements DiaryQueryLikeDsl {
    private final JPAQueryFactory query;

    @Override
    public Member findMemberByDiaryLike(Long diaryId, Long memberId) {
        return query.select(diaryLike.member)
                .from(diaryLike)
                .where(diaryLike.diary.id.eq(diaryId))
                .where(diaryLike.member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Long getDiaryLikeCount(Long diaryId) {
        return query.select(diaryLike.count())
                .from(diaryLike)
                .where(diaryLike.diary.id.eq(diaryId))
                .fetchOne();
    }

    @Override
    public void deleteByMemberIdAndDiaryId(Long diaryId, Long memberId) {
        query.delete(diaryLike)
                .where(diaryLike.member.id.eq(memberId))
                .where(diaryLike.diary.id.eq(diaryId))
                .execute();
    }
}

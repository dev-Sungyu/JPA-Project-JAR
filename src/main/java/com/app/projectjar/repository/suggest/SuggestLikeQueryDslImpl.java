package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.projectjar.entity.suggest.QSuggestLike.suggestLike;

@RequiredArgsConstructor
public class SuggestLikeQueryDslImpl implements SuggestLikeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Member findMemberBySuggestLike(Long suggestId,Long memberId) {
        return query.select(suggestLike.member)
                .from(suggestLike)
                .where(suggestLike.suggest.id.eq(suggestId))
                .where(suggestLike.member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Long getSuggestLikeCount(Long suggestId) {
            return query.select(suggestLike.count())
                    .from(suggestLike)
                    .where(suggestLike.suggest.id.eq(suggestId))
                    .fetchOne();
    }

    @Override
    public void deleteByMemberIdAndSuggestId(Long suggestId, Long memberId) {
        query.delete(suggestLike)
                .where(suggestLike.member.id.eq(memberId))
                .where(suggestLike.suggest.id.eq(suggestId))
                .execute();
    }

}

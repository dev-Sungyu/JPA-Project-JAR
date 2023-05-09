package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    @Override
    public Page<SuggestLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Suggest> foundLike = query.select(suggestLike.suggest)
                .from(suggestLike)
                .leftJoin(suggestLike.suggest)
                .where(suggestLike.member.id.eq(id))
                .orderBy(suggestLike.suggest.createdDate.desc())
                .offset(pageable.getOffset() -1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggestLike.suggest.count())
                .from(suggestLike)
                .where(suggestLike.member.id.eq(id))
                .fetchOne();
        return null;
    }

}

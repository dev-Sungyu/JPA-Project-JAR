package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.file.suggest.QSuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;
import static com.app.projectjar.entity.suggest.QSuggestLike.suggestLike;

@RequiredArgsConstructor
public class SuggestLikeQueryDslImpl implements SuggestLikeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Long findMemberBySuggestLike_QueryDsl(Long suggestId, Long memberId) {
        return query.select(suggestLike.member.count())
                .from(suggestLike)
                .where(suggestLike.suggest.id.eq(suggestId))
                .where(suggestLike.member.memberId.eq(memberId))
                .fetchOne();
    }

    @Override
    public Long getSuggestLikeCount_QueryDsl(Long suggestId) {
            return query.select(suggestLike.count())
                    .from(suggestLike)
                    .where(suggestLike.suggest.id.eq(suggestId))
                    .fetchOne();
    }

    @Override @Transactional
    public void deleteByMemberIdAndSuggestId_QueryDsl(Long suggestId, Long memberId) {
        query.delete(suggestLike)
                .where(suggestLike.member.memberId.eq(memberId).and(suggestLike.suggest.id.eq(suggestId)))
                .execute();
    }

}

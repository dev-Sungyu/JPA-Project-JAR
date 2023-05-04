package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class SuggestQueryDslImpl implements SuggestQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Suggest> findAllWithPaging() {
//           query.select(suggest, suggest.member).from(suggest).fetchJoin(suggest.member.)
        return null;
    }
}

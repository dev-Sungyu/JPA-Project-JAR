package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.List;

import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class SuggestQueryDslImpl implements SuggestQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Suggest> findAllWithPaging(Pageable pageable) {
        List<Suggest> foundSuggest = query.select(suggest)
                .from(suggest)
                .orderBy(suggest.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggest.count())
                .from(suggest)
                .fetchOne();

        return new PageImpl<>(foundSuggest, pageable, count);
    }
}

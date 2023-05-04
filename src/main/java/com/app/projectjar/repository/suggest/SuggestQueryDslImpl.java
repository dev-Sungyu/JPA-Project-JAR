package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class SuggestQueryDslImpl implements SuggestQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Suggest> findAllWithPaging(Pageable pageable) {
        return null;
    }
}

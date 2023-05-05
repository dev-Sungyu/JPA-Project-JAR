package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.suggest.QSuggestReply;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.suggest.QSuggest.suggest;
import static com.app.projectjar.entity.suggest.QSuggestReply.suggestReply;

@RequiredArgsConstructor
public class SuggestReplyQueryDslImpl implements SuggestReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<SuggestReply> findAllBySuggestWithPaging(Long suggestId, Pageable pageable) {
        List<SuggestReply> foundReply = query.select(suggestReply)
                .from(suggestReply)
                .orderBy(suggestReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = getReplyCount(suggestId);

        return new PageImpl<>(foundReply,pageable,count);
    }

    @Override
    public Long getReplyCount(Long suggestId) {
        return query.select(suggestReply.count())
                .from(suggestReply)
                .where(suggestReply.id.eq(suggestId))
                .fetchOne();
    }
}

package com.app.projectjar.repository.suggest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuggestLikeQueryDslImpl implements SuggestLikeQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

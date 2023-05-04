package com.app.projectjar.repository.suggest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuggestQueryDslImpl implements SuggestQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

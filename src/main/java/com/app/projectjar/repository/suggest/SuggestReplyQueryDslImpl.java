package com.app.projectjar.repository.suggest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuggestReplyQueryDslImpl implements SuggestReplyQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

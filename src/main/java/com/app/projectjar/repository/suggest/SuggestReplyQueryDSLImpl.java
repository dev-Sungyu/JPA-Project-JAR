package com.app.projectjar.repository.suggest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuggestReplyQueryDSLImpl implements SuggestReplyQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

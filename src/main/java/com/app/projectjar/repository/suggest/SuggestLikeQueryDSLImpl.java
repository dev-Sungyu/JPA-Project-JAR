package com.app.projectjar.repository.suggest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuggestLikeQueryDSLImpl implements SuggestLikeQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

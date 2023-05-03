package com.app.projectjar.repository.suggest;


import com.app.projectjar.repository.notice.NoticeQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuggestQueryDSLImpl implements SuggestQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

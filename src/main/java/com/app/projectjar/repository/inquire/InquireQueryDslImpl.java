package com.app.projectjar.repository.inquire;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InquireQueryDslImpl implements InquireQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

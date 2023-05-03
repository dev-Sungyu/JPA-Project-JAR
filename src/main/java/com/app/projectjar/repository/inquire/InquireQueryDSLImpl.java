package com.app.projectjar.repository.inquire;


import com.app.projectjar.entity.inquire.Inquire;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InquireQueryDSLImpl implements InquireQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

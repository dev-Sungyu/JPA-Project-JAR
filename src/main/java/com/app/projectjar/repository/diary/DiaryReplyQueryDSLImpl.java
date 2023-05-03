package com.app.projectjar.repository.diary;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryReplyQueryDSLImpl implements DiaryReplyQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

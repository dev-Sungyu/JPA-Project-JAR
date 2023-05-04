package com.app.projectjar.repository.diary;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryReplyQueryDslImpl implements DiaryReplyQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

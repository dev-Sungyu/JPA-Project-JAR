package com.app.projectjar.repository.diary;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryQueryDslImpl implements DiaryQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

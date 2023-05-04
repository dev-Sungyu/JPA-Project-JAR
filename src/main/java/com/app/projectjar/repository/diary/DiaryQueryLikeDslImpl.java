package com.app.projectjar.repository.diary;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryQueryLikeDslImpl implements DiaryQueryLikeDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

package com.app.projectjar.repository.diary;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryQueryLikeDSLImpl implements DiaryQueryLikeDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

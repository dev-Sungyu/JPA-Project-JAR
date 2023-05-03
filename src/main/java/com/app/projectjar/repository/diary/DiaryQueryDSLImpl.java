package com.app.projectjar.repository.diary;


import com.app.projectjar.repository.challenge.ChallengeQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryQueryDSLImpl implements DiaryQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

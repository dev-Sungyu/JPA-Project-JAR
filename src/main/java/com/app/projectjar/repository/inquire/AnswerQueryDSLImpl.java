package com.app.projectjar.repository.inquire;


import com.app.projectjar.repository.challenge.ChallengeQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnswerQueryDSLImpl implements AnswerQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

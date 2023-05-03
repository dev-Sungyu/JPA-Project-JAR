package com.app.projectjar.repository.challenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeQueryDSLImpl implements ChallengeQueryDSL{
    private final JPAQueryFactory jpaQueryFactory;
}

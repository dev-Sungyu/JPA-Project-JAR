package com.app.projectjar.repository.challenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeAttendQueryDSLImpl implements ChallengeAttendQueryDSL{
    private final JPAQueryFactory jpaQueryFactory;
}

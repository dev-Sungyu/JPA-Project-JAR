package com.app.projectjar.repository.challenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeAttendQueryDslImpl implements ChallengeAttendQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

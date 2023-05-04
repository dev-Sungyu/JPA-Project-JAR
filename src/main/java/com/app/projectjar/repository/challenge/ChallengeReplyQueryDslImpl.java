package com.app.projectjar.repository.challenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeReplyQueryDslImpl implements ChallengeReplyQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

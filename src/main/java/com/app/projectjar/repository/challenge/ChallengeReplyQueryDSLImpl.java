package com.app.projectjar.repository.challenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeReplyQueryDSLImpl implements ChallengeReplyQueryDSL{
    private final JPAQueryFactory jpaQueryFactory;
}

package com.app.projectjar.repository.groupChallenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupChallengeQueryDslImpl implements GroupChallengeQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

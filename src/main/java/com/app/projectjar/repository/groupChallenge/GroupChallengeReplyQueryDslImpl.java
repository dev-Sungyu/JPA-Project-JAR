package com.app.projectjar.repository.groupChallenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupChallengeReplyQueryDslImpl implements GroupChallengeReplyQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
}

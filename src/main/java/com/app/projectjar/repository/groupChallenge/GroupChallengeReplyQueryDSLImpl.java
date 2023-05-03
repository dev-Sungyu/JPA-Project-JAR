package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.repository.challenge.ChallengeQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupChallengeReplyQueryDSLImpl implements GroupChallengeReplyQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

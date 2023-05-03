package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.repository.challenge.ChallengeReplyQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupChallengeQueryDSLImpl implements GroupChallengeQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

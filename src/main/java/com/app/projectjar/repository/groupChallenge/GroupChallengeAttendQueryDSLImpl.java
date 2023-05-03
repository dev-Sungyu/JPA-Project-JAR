package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.repository.challenge.ChallengeReplyQueryDSL;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupChallengeAttendQueryDSLImpl implements GroupChallengeAttendQueryDSL {
    private final JPAQueryFactory jpaQueryFactory;
}

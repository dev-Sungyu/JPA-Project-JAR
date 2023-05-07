package com.app.projectjar.repository.groupChallenge;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupChallengeAttendQueryDslImpl implements GroupChallengeAttendQueryDsl {
    private final JPAQueryFactory query;
}

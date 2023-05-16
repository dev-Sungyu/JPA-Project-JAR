package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.challenge.QChallenge;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.entity.personalChallenge.QPersonalChallenge;
import com.app.projectjar.type.ChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.personalChallenge.QPersonalChallenge.personalChallenge;

@RequiredArgsConstructor
public class PersonalChallengeQueryDslImpl implements PersonalChallengeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<PersonalChallenge> findAllByChallengeStatusToOpen() {
        List<PersonalChallenge> personalChallengeList = query.select(personalChallenge)
                .from(personalChallenge)
                .leftJoin(personalChallenge.challenge, challenge)
                .fetchJoin()
                .where(personalChallenge.challengeStatus.eq(ChallengeType.valueOf("OPEN")))
                .fetch();

        return personalChallengeList;
    }
}

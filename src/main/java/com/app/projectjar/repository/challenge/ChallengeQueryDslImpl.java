package com.app.projectjar.repository.challenge;


import com.app.projectjar.entity.challenge.Challenge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ChallengeQueryDslImpl implements ChallengeQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public List<Challenge> findAllByChallengeTypeToWait() {
        return null;
    }

    @Override
    public List<Challenge> findAllByChallengeTypeToOpen() {
        return null;
    }

    @Override
    public List<Challenge> findAllByChallengeTypeToPrivate() {
        return null;
    }

    @Override
    public Optional<Challenge> findByChallengeId(Long challengeId) {
        return Optional.empty();
    }
}

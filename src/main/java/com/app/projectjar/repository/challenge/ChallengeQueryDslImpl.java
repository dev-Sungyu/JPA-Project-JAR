package com.app.projectjar.repository.challenge;


import com.app.projectjar.domain.dto.BoardDetailDTO;
import com.app.projectjar.domain.dto.FileDTO;
import com.app.projectjar.domain.dto.QBoardDetailDTO;
import com.app.projectjar.domain.dto.QFileDTO;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.type.ChallengeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.file.challenge.QChallengeFile.challengeFile;
import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;

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

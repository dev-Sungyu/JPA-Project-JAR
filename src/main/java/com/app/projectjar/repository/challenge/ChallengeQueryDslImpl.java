package com.app.projectjar.repository.challenge;


import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.challenge.QChallenge;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.challenge.QChallenge.challenge;
import static com.app.projectjar.entity.file.challenge.QChallengeFile.challengeFile;

@RequiredArgsConstructor
public class ChallengeQueryDslImpl implements ChallengeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<Challenge> findAll_QueryDsl() {
        return query.select(challenge)
                .from(challenge)
                .distinct()
                .leftJoin(challenge.challengeFiles, challengeFile)
                .fetchJoin()
                .fetch();
    }
}

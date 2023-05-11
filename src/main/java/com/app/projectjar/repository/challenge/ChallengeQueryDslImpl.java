package com.app.projectjar.repository.challenge;


import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.challenge.Challenge;
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


    @Override
    public Page<Challenge> findAllChallengeWithPaging_QueryDsl(Pageable pageable) {
        List<Challenge> foundChallenge = query.select(challenge)
                .from(challenge)
                .leftJoin(challenge.challengeFiles, challengeFile)
                .fetchJoin()
                .orderBy(challenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(challenge.count())
                .from(challenge)
                .fetchOne();

        return new PageImpl<>(foundChallenge, pageable, count);
    }

    /*검색*/
    @Override
    public List<Challenge> findAllWithSearch(BoardSearch boardSearch) {
        BooleanExpression challengeTitleLike = boardSearch.getBoardTitle() == null ? null : challenge.boardTitle.like(boardSearch.getBoardTitle());

        List<Challenge> products = query.select(challenge)
                .from(challenge)
                .where(challengeTitleLike)
                .leftJoin(challenge.challengeFiles, challengeFile)
                .orderBy(challenge.id.desc())
                .fetch();

        return products;
    }
}

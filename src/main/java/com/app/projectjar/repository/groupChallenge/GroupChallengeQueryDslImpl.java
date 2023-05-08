package com.app.projectjar.repository.groupChallenge;


import com.app.projectjar.domain.dto.*;
import com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.entity.groupChallenge.QGroupChallenge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.groupChallenge.QGroupChallengeFile.groupChallengeFile;
import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.groupChallenge.QGroupChallenge.groupChallenge;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;

@RequiredArgsConstructor
public class GroupChallengeQueryDslImpl implements GroupChallengeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<GroupChallenge> findAllGroupChallengeWithPaging_QueryDsl(Pageable pageable) {
        List<GroupChallenge> foundGroupChallenge = query.select(groupChallenge)
                .from(groupChallenge)
                .leftJoin(groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .orderBy(groupChallenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(groupChallenge.count())
                .from(groupChallenge)
                .fetchOne();

        return new PageImpl<>(foundGroupChallenge, pageable, count);
    }

    @Override
    public Optional<GroupChallenge> findByGroupChallengeId_QueryDsl(Long groupChallengeId) {
        GroupChallenge groupChallenge = query.select(QGroupChallenge.groupChallenge)
                .from(QGroupChallenge.groupChallenge)
                .leftJoin(QGroupChallenge.groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .where(QGroupChallenge.groupChallenge.id.eq(groupChallengeId))
                .fetchOne();

        return Optional.ofNullable(groupChallenge);
    }

    // 목록(페이징 처리 없는 버전)
    @Override
    public List<GroupChallenge> findAllGroupChallenge_QueryDsl() {
        return query.select(groupChallenge)
                .from(groupChallenge)
                .leftJoin(groupChallenge.groupChallengeFiles, groupChallengeFile)
                .fetchJoin()
                .orderBy(groupChallenge.id.desc())
                .fetch();
    }

}

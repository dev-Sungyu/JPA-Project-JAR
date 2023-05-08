package com.app.projectjar.repository.suggest;


import com.app.projectjar.domain.dto.*;
import com.app.projectjar.entity.file.suggest.QSuggestFile;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.QMember;
import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.io.File;
import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;


@RequiredArgsConstructor
public class SuggestQueryDslImpl implements SuggestQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Suggest> findAllWithPaging_QueryDsl(Pageable pageable) {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .orderBy(suggest.id.desc())
                .offset(pageable.getOffset() - 1)
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggest.count()).from(suggest).fetchOne();

        return new PageImpl<>(foundSuggests, pageable, count);
    }

    @Override
    public Optional<Suggest> findByIdSuggest_QueryDsl(Long suggestId) {
        Suggest foundSuggest = query.select(suggest)
                .from(QSuggest.suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .leftJoin(suggest.member, member)
                .fetchJoin()
                .where(suggest.id.eq(suggestId))
                .fetchOne();

        return Optional.ofNullable(foundSuggest);
    }


    @Override
    public Page<Suggest> findAllByMemberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Suggest> foundSuggest = query.select(suggest)
                .from(QSuggest.suggest)
                .where(suggest.member.id.eq(id))
                .orderBy(suggest.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggest.count())
                .from(suggest)
                .where(suggest.member.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundSuggest, pageable, count);
    }
}

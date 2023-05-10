package com.app.projectjar.repository.suggest;


import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.suggest.QSuggest;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.type.BoardType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.projectjar.entity.file.suggest.QSuggestFile.suggestFile;
import static com.app.projectjar.entity.member.QMember.member;
import static com.app.projectjar.entity.suggest.QSuggest.suggest;


@RequiredArgsConstructor
public class SuggestQueryDslImpl implements SuggestQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<Suggest> findByPersonalWithPaging_QueryDsl(Pageable pageable) {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .where(suggest.boardType.eq(BoardType.PERSONAL))
                .orderBy(suggest.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(suggest.count()).from(suggest).fetchOne();

        return new PageImpl<>(foundSuggests, pageable, count);
    }

    @Override
    public Page<Suggest> findByGroupWithPaging_QueryDsl(Pageable pageable) {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .where(suggest.boardType.eq(BoardType.GROUP))
                .orderBy(suggest.id.desc())
                .offset(pageable.getOffset())
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
    public Suggest getCurrentSequence() {
        return query.select(suggest)
                .from(suggest)
                .orderBy(suggest.id.desc())
                .limit(1)
                .fetchOne();
    }


    @Override
    public Page<Suggest> findAllByMemberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<Suggest> foundSuggest = query.select(suggest)
                .from(suggest)
                .where(suggest.member.id.eq(id))
                .leftJoin(suggest.suggestFiles)
                .fetchJoin()
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

    /*관리자 페이지*/
    @Override
    public List<Suggest> findByPersonal_QueryDsl() {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .where(suggest.boardType.eq(BoardType.PERSONAL))
                .orderBy(suggest.id.desc())
                .fetch();

        return foundSuggests;
    }

    @Override
    public List<Suggest> findByGroup_QueryDsl() {
        List<Suggest> foundSuggests = query.select(suggest)
                .from(suggest)
                .leftJoin(suggest.suggestFiles, suggestFile)
                .fetchJoin()
                .where(suggest.boardType.eq(BoardType.GROUP))
                .orderBy(suggest.id.desc())
                .fetch();

        return foundSuggests;
    }

    /*검색*/
    @Override
    public List<Suggest> findAllWithSearch(BoardSearch boardSearch) {
        BooleanExpression suggestTitleLike = boardSearch.getBoardTitle() == null ? null : suggest.boardTitle.like(boardSearch.getBoardTitle());

        List<Suggest> products = query.select(suggest)
                .from(suggest)
                .where(suggestTitleLike)
                .orderBy(suggest.id.desc())
                .fetch();

        return products;
    }
}

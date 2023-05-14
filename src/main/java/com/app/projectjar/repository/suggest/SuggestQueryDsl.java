package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.board.BoardSearch;
import com.app.projectjar.entity.suggest.Suggest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SuggestQueryDsl {
    // 전체 개인 조회
    public Page<Suggest> findByBoardTypeWithPaging_QueryDsl(String boardType, Pageable pageable);

    // 상세보기
    public Optional<Suggest> findByIdSuggest_QueryDsl(Long suggestId);

    // 현재 시퀀스 가져오기
    public Suggest getCurrentSequence_QueryDsl();


    /* 마이 페이지*/
//    제안 내가 작성한 글 게시글 전체 조회
    public Page<Suggest> findAllByMemberIdWithPaging_QueryDsl(Pageable pageable, Long id);


    /*관리자 페이지*/
    // 전체 조회
    public Page<Suggest> findAllWithPaging_QueryDsl(Pageable pageable);


    //    검색
    public List<Suggest> findAllWithSearch(BoardSearch boardSearch);


}

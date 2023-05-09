package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.entity.suggest.Suggest;
import org.aspectj.weaver.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SuggestQueryDsl {
    // 전체 조회
    public Page<Suggest> findAllWithPaging_QueryDsl(Pageable pageable);

    // 상세보기
    public Optional<Suggest> findByIdSuggest_QueryDsl(Long suggestId);

    // 현재 시퀀스 가져오기
    public Suggest getCurrentSequence();


    /* 마이 페이지*/
//    제안 내가 작성한 글 게시글 전체 조회
    public Page<Suggest> findAllByMemberIdWithPaging_QueryDsl(Pageable pageable, Long id);

}

package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.Suggest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SuggestQueryDsl {
    // 전체 조회
    public Page<Suggest> findAllWithPaging_QueryDsl(Pageable pageable);

    // 상세보기
    public Optional<Suggest> findByIdSuggest_QueryDsl(Long suggestId);
}

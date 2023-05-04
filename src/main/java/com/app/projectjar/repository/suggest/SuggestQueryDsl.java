package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.Suggest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SuggestQueryDsl {
    // 전체 조회
    public Page<Suggest> findAllWithPaging();
    // 상세 조회 (작성자 포함)
}

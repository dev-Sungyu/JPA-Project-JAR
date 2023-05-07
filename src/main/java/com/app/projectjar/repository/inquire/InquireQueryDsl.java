package com.app.projectjar.repository.inquire;

import com.app.projectjar.entity.inquire.Inquire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InquireQueryDsl {
//    전체조회
    public Page<Inquire> findAllWithPaging(Pageable pageable);
}

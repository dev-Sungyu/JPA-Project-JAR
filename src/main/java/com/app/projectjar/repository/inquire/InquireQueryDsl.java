package com.app.projectjar.repository.inquire;

import com.app.projectjar.entity.inquire.Inquire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InquireQueryDsl {
//    전체조회
    public Page<Inquire> findAllWithPaging(Pageable pageable);



    /* 마이 페이지  */

//    문의 게시글 전체 조회 (문의 게시글 답변 체크 후 스테이스 변경)
    public Page<Inquire> findAllByMemberIdWithPaging(Pageable pageable, Long id);

//    문의 게시글 삭제

}

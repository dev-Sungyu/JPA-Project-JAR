package com.app.projectjar.repository.diary;

import com.app.projectjar.domain.dto.ReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Page<ReplyDTO> findAllByDiaryWithPaging(Long suggestId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount(Long suggestId);
    // 게시판 정보
}

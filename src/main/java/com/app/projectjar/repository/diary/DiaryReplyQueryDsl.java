package com.app.projectjar.repository.diary;

import com.app.projectjar.domain.dto.ReplyDTO;
import com.app.projectjar.entity.diary.DiaryReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Page<DiaryReply> findAllByDiaryWithPaging_QueryDsl(Long diaryId, Pageable pageable);
    // 댓글 갯수
    public Long getReplyCount_QueryDsl(Long diaryId);
    // 게시판 정보
}

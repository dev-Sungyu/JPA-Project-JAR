package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DiaryQueryDsl {

    // save
    // 상세보기
    public Optional<Diary> findByDiaryId_QueryDsl(Long diaryId);
    // 리스트
    public Page<Diary> findAllDiary_QueryDsl(Pageable pageable);


    /* 마이 페이지 */
//    내가 작성한 다이어리 게시판 전체 조회
    public Page<Diary> findAllByMemberWithPaging_QueryDsl(Pageable pageable, Long id);
}

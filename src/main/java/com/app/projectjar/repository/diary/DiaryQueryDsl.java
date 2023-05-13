package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface DiaryQueryDsl {

    // save
    // 상세보기
    public Optional<Diary> findByDiaryId_QueryDsl(Long diaryId);
    // 리스트
    public Slice<Diary> findAllDiary_QueryDsl(String sort, Pageable pageable);


    /* 마이 페이지 */
//    내가 작성한 다이어리 게시판 전체 조회
    public Page<Diary> findAllByMemberWithPaging_QueryDsl(Pageable pageable, Long id);

//    내가 좋아요한 다이어리 게시판 전체조회
    public Page<Diary> findByLikeMEmberIdWithPaging_QueryDsl(Pageable pageable, Long id);
}

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
}

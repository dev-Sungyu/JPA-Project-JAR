package com.app.projectjar.repository.diary;

import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.domain.dto.BoardDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryQueryDsl {

    // save
    // 상세보기
    public BoardDetailDTO findByDiaryId(Long diaryId);
    // 리스트
    public Page<BoardDTO> findAllDiary(Pageable pageable);
}

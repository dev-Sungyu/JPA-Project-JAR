package com.app.projectjar.service.diary;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.reply.ReplyDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("diary") @Primary
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    @Override
    public Slice<DiaryDTO> getOpenDiaryList(String sort, Pageable pageable) {
        Slice<Diary> diaryList = diaryRepository.findAllDiary_QueryDsl(sort, pageable);

        List<DiaryDTO> diaryDTOS = diaryList.getContent().stream().map(this::toDiaryDTO).collect(Collectors.toList());

        return new SliceImpl<>(diaryDTOS, pageable, diaryList.hasNext());
    }

    @Override
    public DiaryDTO getDiary(Long diaryId) {
        Optional<Diary> diary = diaryRepository.findByDiaryId_QueryDsl(diaryId);
        return toDiaryDTO(diary.get());
    }
}

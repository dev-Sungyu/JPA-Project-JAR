package com.app.projectjar.service.diary;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.repository.diary.DiaryRepository;
import com.app.projectjar.repository.file.diary.DiaryFileRepository;
import com.app.projectjar.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("diary") @Primary
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    private final DiaryFileRepository diaryFileRepository;

    @Override
    public Slice<DiaryDTO> getOpenDiaryList(String sort, Pageable pageable) {
        Slice<Diary> diaryList = diaryRepository.findByMemberIdDiary_QueryDsl(sort, pageable);

        List<DiaryDTO> diaryDTOS = diaryList.getContent().stream().map(this::toDiaryDTO).collect(Collectors.toList());
        return new SliceImpl<>(diaryDTOS, pageable, diaryList.hasNext());
    }

    @Override
    public DiaryDTO getDiary(Long diaryId) {
        Optional<Diary> diary = diaryRepository.findByDiaryId_QueryDsl(diaryId);
        return toDiaryDTO(diary.get());
    }

    @Override
    public Page<DiaryDTO> getDiaryForMemberIdList(Pageable pageable, Long id) {
        Page<Diary> diaries = diaryRepository.findAllByMemberWithPaging_QueryDsl(pageable, id);
        List<DiaryDTO> diaryDTOS = diaries.stream().map(this::toDiaryDTO).collect(Collectors.toList());
        return new PageImpl<>(diaryDTOS, diaries.getPageable(), diaries.getTotalElements());
    }

    @Override @Transactional
    public void modifyDiary(DiaryDTO diaryDTO) {
        List<FileDTO> fileDTOS = diaryDTO.getFileDTOS();

        diaryRepository.findById(diaryDTO.getId()).ifPresent(
                diary -> {
                    diary.builder()
                            .boardContent(diaryDTO.getBoardContent())
                            .boardTitle(diaryDTO.getBoardTitle())
                            .member(diary.getMember())
                            .diaryStatus(diaryDTO.getDiaryStatus())
                            .build();
                    diaryRepository.save(diary);
                }
        );
        diaryFileRepository.deleteByDiaryId(diaryDTO.getId());

        if(fileDTOS != null){
            for (int i = 0; i < fileDTOS.size(); i++) {
                if(i == 0){
                    fileDTOS.get(i).setFileType(FileType.REPRESENTATIVE);
                }else {
                    fileDTOS.get(i).setFileType(FileType.NORMAL);
                }
                fileDTOS.get(i).setDiary(getCurrentSequence());
                diaryFileRepository.save(toDiaryFileEntity(fileDTOS.get(i)));
            }
        }
    }

    @Override
    public Diary getCurrentSequence() {
        return diaryRepository.getCurrentSequence_QueryDsl();
    }
}

package com.app.projectjar.service.mypage;

import com.app.projectjar.domain.calendar.CalendarDTO;
import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.repository.diary.DiaryRepository;
import com.app.projectjar.repository.file.diary.DiaryFileRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("myPage") @Primary
public class MyPageServiceImpl implements MyPageService {

    private final MemberRepository memberRepository;

    private final DiaryRepository diaryRepository;

    private final DiaryFileRepository diaryFileRepository;

    @Override
    public void registerDiary(DiaryDTO diaryDTO, Long memberId) {
        List<FileDTO> fileDTOS = diaryDTO.getFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> diaryDTO.setMemberDTO(toMemberDTO(member))
        );
        diaryRepository.save(toDiaryEntity(diaryDTO));

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

    @Override
    public List<CalendarDTO> getCalendarDTO(Long memberId) {
        List<CalendarDTO> calendarDTOS = new ArrayList<>();

        diaryRepository.findByMemberIdDiary_QueryDsl(memberId).stream().forEach(
                diary -> {
                    CalendarDTO calendarDTO = CalendarDTO.builder()
                            .end(diary.getCreatedDate())
                            .start(diary.getCreatedDate())
                            .title(diary.getBoardTitle())
                            .id(diary.getId())
                            .build();
                    calendarDTOS.add(calendarDTO);
                }
        );
        return calendarDTOS;
    }

    @Override
    public DiaryDTO getDiary(Long diaryId) {
        return toDiaryDTO(diaryRepository.findByDiaryId_QueryDsl(diaryId).get());
    }
}

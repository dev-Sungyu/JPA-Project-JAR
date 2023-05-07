package com.app.projectjar.repository.diary;

import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.DiaryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class DiaryRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    public void saveTest() {
        memberRepository.findById(1L).ifPresent(
                member ->
                        diaryRepository.save(
                                new Diary(
                                        "테스트 제목1",
                                        "테스트 내용1",
                                        DiaryType.PRIVATE,
                                        member
                                )
                        )
        );
    }

    @Test
    public void findByDiaryId() {
        log.info(diaryRepository.findByDiaryId(110L).toString());
    }

    @Test
    public void findAllDiaryTest() {
        PageRequest pageRequest = PageRequest.of(0,10);
        diaryRepository.findAllDiary(pageRequest).stream().map(BoardDTO::toString)
                .forEach(log::info);
    }
}

package com.app.projectjar.repository.diary;

import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

//    @Test
//    public void saveTest() {
//        memberRepository.findById(1L).ifPresent(
//                member ->
//                        diaryRepository.save(
//                                new Diary()
//                        );
//        );
//    }
}

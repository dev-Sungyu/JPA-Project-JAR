package com.app.projectjar.inquireRepositoryTests;


import com.app.projectjar.entity.inquire.Inquire;
import com.app.projectjar.repository.inquire.InquireRepository;
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
public class InquireRepositoryTests {
    @Autowired
    private InquireRepository inquireRepository;

    //문의사항 작성
    @Test
    public void saveTest(){
        Inquire inquire = new Inquire(1L, "테스트 제목", "테스트 내용");
        inquireRepository.save(inquire);
    }

    //문의사항 리스트
    @Test
    public void findAllWithPagingTest(){
        PageRequest pageRequest = PageRequest.of(0,10);
        inquireRepository.findAllWithPaging(pageRequest).stream().map(Inquire::toString).forEach(log::info);
    }

    //문의사항 상세보기
    @Test
    public void findByInquireIdTest(){ inquireRepository.findById(2L).map(Inquire::toString).ifPresent(log::info); }

    //문의사항 삭제하기
    @Test
    public void deleteTest(){ inquireRepository.findById(2L).ifPresent(inquire -> inquireRepository.delete(inquire));}

    //문의사항 수정하기
    @Test
    public void updateTest(){ inquireRepository.findById(2L).ifPresent(inquire -> inquire.setInquireTitle("수정한 타이틀1"));}

}

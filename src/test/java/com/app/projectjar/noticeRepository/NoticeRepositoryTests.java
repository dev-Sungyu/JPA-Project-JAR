package com.app.projectjar.noticeRepository;

import com.app.projectjar.entity.notice.Notice;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.repository.notice.NoticeRepository;
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
public class NoticeRepositoryTests {
    @Autowired
    private NoticeRepository noticeRepository;


//    공지사항 리스트
    @Test
    public void findAllWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(0,10);
        noticeRepository.findAllWithPaging(pageRequest).stream().map(Notice::toString).forEach(log::info);
    }

//    공지사항 상세보기
    @Test
    public void findByNoticeIdTest(){
        noticeRepository.findById(2L).map(Notice::toString).ifPresent(log::info);
    }

//    공지사항 삭제하기
    @Test
    public void deleteTest() {
        noticeRepository.findById(2L).ifPresent(notice -> noticeRepository.delete(notice));
    }
}

package com.app.projectjar.repository.suggest;

import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.domain.dto.BoardDetailDTO;
import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.repository.file.suggest.SuggestFileRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.BoardType;
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
public class SuggestRepositoryTests {

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void saveTest() {
        for (int i = 0; i < 100; i++) {
            memberRepository.findById(1L).ifPresent(
                    member ->
                            suggestRepository.save(new Suggest("테스트 제목","테스트 내용",BoardType.PERSONAL,member)));
        }
    }

    @Test
    public void findAllWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(7,10);
        suggestRepository.findAllWithPaging(pageRequest).stream().map(BoardDTO::toString).forEach(log::info);
    }

    @Test
    public void findByIdSuggest() {
        suggestRepository.findByIdSuggest(26L).map(BoardDetailDTO::toString).ifPresent(log::info);
    }

    /*관리자 페이지*/
    //    제안 게시판 삭제하기
    @Test
    public void deleteTest() {
        suggestRepository.findById(2L).ifPresent(notice -> suggestRepository.delete(notice));
    }

//    제안 게시판 수정하기


}

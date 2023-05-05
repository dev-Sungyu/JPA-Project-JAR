package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.BoardType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        PageRequest pageRequest = PageRequest.of(0,10);
        suggestRepository.findAllWithPaging(pageRequest).stream().map(Suggest::toString).forEach(log::info);
    }


    @Test
    public void findByIdTest() {
        suggestRepository.findById(60L).map(Suggest::toString).ifPresent(log::info);
    }
}

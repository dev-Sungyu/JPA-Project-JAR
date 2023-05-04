package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.board.Board;
import com.app.projectjar.entity.suggest.Suggest;
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
public class SuggestRepositoryTests {

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest() {
        Suggest suggest = new Suggest("테스트 제목1", "테스트 내용2");

        memberRepository.findById(1L).ifPresent(member -> suggest.setMember(member));
        suggestRepository.save(suggest);
    }

}

package com.app.projectjar.repository.suggest;

import com.app.projectjar.entity.suggest.SuggestReply;
import com.app.projectjar.repository.member.MemberRepository;
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
public class SuggestReplyRepositoryTests {

    @Autowired
    private SuggestReplyRepository suggestReplyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SuggestRepository suggestRepository;

    @Test
    public void saveTest() {
        suggestRepository.findById(40L).ifPresent(
                suggest ->
                        memberRepository.findById(1L).ifPresent(
                                member ->
                                        suggestReplyRepository.save(new SuggestReply("테스트 댓글1",suggest, member))
                        )
        );
    }

    @Test
    public void findAllBySuggestWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(0,10);
        suggestReplyRepository.findAllBySuggestWithPaging(40L,pageRequest)
                .stream()
                .map(SuggestReply::toString)
                .forEach(log::info);
    }

    @Test
    public void updateTest() {
        suggestReplyRepository.findById(104L).ifPresent(
                suggestReply -> suggestReply.setSuggestReplyContent("수정된 댓글1")
        );
    }

    @Test
    public void deleteTest() {
        suggestReplyRepository.findById(104L).ifPresent(
                suggestReply -> suggestReplyRepository.delete(suggestReply)
        );
    }
}

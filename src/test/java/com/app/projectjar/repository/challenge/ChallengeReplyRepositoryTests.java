package com.app.projectjar.repository.challenge;

import com.app.projectjar.domain.ReplyDTO;
import com.app.projectjar.entity.challenge.ChallengeReply;
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
public class ChallengeReplyRepositoryTests {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChallengeReplyRepository challengeReplyRepository;

    @Test
    public void saveTest() {
        challengeRepository.findById(212L).ifPresent(
                challenge ->
                        memberRepository.findById(1L).ifPresent(
                                member -> {
                                    for (int i = 0; i < 10; i++) {
                                        challengeReplyRepository.save(new ChallengeReply("챌린지 댓글" + (i + 1),challenge,member));
                                    }
                                }
                        )
        );
    }

    @Test
    public void findAllByChallengeWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        challengeRepository.findById(212L).ifPresent(
                challenge -> {
                    challengeReplyRepository.findAllByChallengeWithPaging(challenge.getId(),pageRequest).map(ReplyDTO::toString).forEach(log::info);
                }
        );
    }
}

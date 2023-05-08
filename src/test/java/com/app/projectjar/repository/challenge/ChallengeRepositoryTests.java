package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.type.ChallengeType;
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
public class ChallengeRepositoryTests {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Test
    public void saveTest(){
        for (int i = 0; i < 5; i++) {
            challengeRepository.save(new Challenge("개인 챌린지 테스트 제목" + (i + 1), "개인 챌린지 테스트 내용" + (i + 1), ChallengeType.PRIVATE));
        }
    }

    @Test
    public void findAllByChallengeTypeToOpenTest() {
        log.info(challengeRepository.findAllByChallengeTypeToOpen().size() + "개");
    }

    @Test
    public void findAllByChallengeTypeToPrivateTest() {
//        log.info(challengeRepository.findAllByChallengeTypeToPrivate().size() + "개 ");
        challengeRepository.findAllByChallengeTypeToPrivate().stream().map(Challenge::toString).forEach(log::info);
    }

    @Test
    public void findALlByChallengeTypeToWaitTest() {
        log.info(challengeRepository.findAllByChallengeTypeToWait().size() + "개 ");
    }
}

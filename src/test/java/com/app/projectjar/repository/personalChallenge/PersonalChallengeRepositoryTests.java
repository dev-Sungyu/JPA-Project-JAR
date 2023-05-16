package com.app.projectjar.repository.personalChallenge;

import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import com.app.projectjar.repository.challenge.ChallengeRepository;
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
public class PersonalChallengeRepositoryTests {

    @Autowired
    private PersonalChallengeRepository personalChallengeRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Test
    public void saveTest(){
        for (int i = 149; i < 151; i++) {
            challengeRepository.findById(Long.valueOf(i)).ifPresent(
                    challenge -> {
                        PersonalChallenge personalChallenge = PersonalChallenge.builder()
                                .challenge(challenge)
                                .build();
                        personalChallengeRepository.save(personalChallenge);
                    }
            );
        }
    }

    @Test
    public void findAllByChallengeStatusToOpenTest(){
        personalChallengeRepository.findAllByChallengeStatus().stream().map(PersonalChallenge::getChallengeStatus).forEach(challengeType -> log.info(challengeType + ""));
    }
}

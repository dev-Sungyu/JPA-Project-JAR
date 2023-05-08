package com.app.projectjar.challengeRepositoryTests;

import com.app.projectjar.repository.challenge.ChallengeRepository;
import lombok.extern.slf4j.Slf4j;
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

//    @Test
//    public void saveTest(){
//        Challenge challenge = new Challenge("jpa 도장깨기","항복", ChallengeType.OPEN,"");
//    }
}

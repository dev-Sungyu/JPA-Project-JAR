package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.file.challenge.ChallengeFile;
import com.app.projectjar.repository.challenge.ChallengeRepository;
import com.app.projectjar.repository.file.challenge.ChallengeFileRepository;
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
public class ChallengeFileRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeFileRepository challengeFileRepository;

//    @Test
//    public void saveTest(){
//        ChallengeFile challengeFile = new ChallengeFile("챌린지테스트1","123-11","/upload","")
//        challengeFileRepository.save(challengeFile);
//    }

}

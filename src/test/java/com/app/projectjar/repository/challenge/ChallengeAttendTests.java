package com.app.projectjar.repository.challenge;

import com.app.projectjar.entity.challenge.ChallengeAttend;
import com.app.projectjar.repository.challenge.ChallengeAttendRepository;
import com.app.projectjar.repository.member.MemberRepository;
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
public class ChallengeAttendTests {
    @Autowired
    private ChallengeAttendRepository challengeAttendRepository;
    @Autowired
    private MemberRepository memberRepository;

//    @Test
//    public void saveTest() {
//        ChallengeAttend challengeAttend = new ChallengeAttend(ChallengeType.OPEN, memberRepository.findByMemberId(1L).get(), "테스트1");
//    }

}

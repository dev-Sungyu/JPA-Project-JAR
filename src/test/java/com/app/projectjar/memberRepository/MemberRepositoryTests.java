package com.app.projectjar.memberRepository;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.BedgeType;
import com.app.projectjar.type.MemberType;
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
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        Member member = new Member("user01@naver.com","qwer1234!","010-1234-5678","최규선","최팀장", MemberType.ENABLE ,BedgeType.ONE);
        memberRepository.save(member);
    }

    @Test
    public void findByIdTest(){
        memberRepository.findById(1L).toString();
    }
}

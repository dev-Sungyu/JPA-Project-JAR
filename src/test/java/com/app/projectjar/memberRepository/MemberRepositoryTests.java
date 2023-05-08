package com.app.projectjar.memberRepository;

import com.app.projectjar.domain.dto.QMemberDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.domain.dto.MemberDTO;
import com.app.projectjar.repository.file.member.MemberFIleRepository;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.BadgeType;
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

    @Autowired
    private MemberFIleRepository memberFIleRepository;


//      추가
    @Test
    public void saveTest() {
        MemberFile memberFile = new MemberFile("", "", "");
        Member member = new Member("user011@naver.com", "qwer1234!", "010-1231-5678", "최규선", "최팀장", MemberType.ENABLE, BadgeType.ONE, memberFile);
        memberRepository.save(member);
        memberFIleRepository.save(memberFile);
    }

//      조회
    @Test
    public void findByMemberDTOIdTest() {
        memberRepository.findByMemberDTOId(1L).map(MemberDTO::toString).ifPresent(log::info);
    }

//        수정
    @Test
    public void updateTest() {
        memberRepository.findByMemberDTOId(3L).ifPresent(
                member -> {
                    member.setMemberName("바보맥");
                    member.setMemberNickname("ㅇㅇㅇㅇㅇ");
                    member.setMemberPhoneNumber("01012321256");
                    memberRepository.updateMember(member.toEntity(member));
                });
    }

//      삭제
    @Test
    public void deleteMemberTest(){
        memberRepository.findByMemberId(3L).ifPresent(memberRepository::delete);
    }

//    이메일 중복 검사
    @Test
    public void overlapByMemberEmailTest(){
//        memberRepository.overlapByMemberEmail("user01@naver.com").ifPresent(member -> member.getMemberEmail());
        memberRepository.overlapByMemberEmail("user04@naver.com").map(Member::getMemberEmail).ifPresent(log::info);
        log.info("@@@@@@@@@@@@@@@@@@");
    }

//    핸드폰 중복 검사
    @Test
    public void overlapByPhoneNumberTest(){
        memberRepository.overlapByPhoneNumber("010-4444-5678").map(Member::getMemberPhoneNumber).ifPresent(log::info);
        log.info("@@@@@@@@@@@@@@@@@@");
    }

//    비밀번호 찾기
    @Test
    public void findByMemberEmailForPasswordTest(){
        memberRepository.findByMemberEmailForPassword("user01@naver.com").map(Member::getMemberEmail).ifPresent(log::info);
    }

//    비밀번호 변경
    @Test
    public void updatePasswordTest(){
        memberRepository.updatePassword(1L, "qwer1234@");
    }

//    뱃지 타입 업데이트
    @Test
    public void updateMemberBadgeTest(){
        memberRepository.updateMemberBadge(1L, BadgeType.ONE);
    }


}

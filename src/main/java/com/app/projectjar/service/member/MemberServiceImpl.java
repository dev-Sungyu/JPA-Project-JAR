package com.app.projectjar.service.member;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("member") @Primary
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail_QueryDSL(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        log.info(member.getMemberType().name());

        return UserDetail.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberName(member.getMemberName())
                .memberNickName(member.getMemberNickname())
                .badgeType(member.getBadgeType())
                .memberType(member.getMemberType())
                .build();
    }

    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberType(Role.MEMBER);
        memberRepository.save(toMemberEntity(memberDTO));
    }

    @Override
    public Long checkEmail(String memberEmail) {
//        return memberRepository.overlapByMemberEmail_QueryDSL(memberEmail).ifPresent(member -> toMemberEntity(memberEmail));
        return null;
    }

    @Override
    public Long checkPhoneNumber(String memberPhoneNumber) {
        return null;
    }
}

package com.app.projectjar.service.member;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService extends UserDetailsService {
//    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder().
                id(memberDTO.getMemberId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberStatus(memberDTO.getMemberStatus())
                .badgeType(memberDTO.getBadgeType())
                .memberType(memberDTO.getMemberType())
                .build();
    }
}

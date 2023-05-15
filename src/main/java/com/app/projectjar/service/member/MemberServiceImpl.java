package com.app.projectjar.service.member;

import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.provider.UserDetail;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    회원가입
    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberType(Role.MEMBER);
        memberRepository.save(toMemberEntity(memberDTO));
    }

//    이메일 중복검사
    @Override
    public Long checkEmail(String memberEmail) {
//        return memberRepository.overlapByMemberEmail_QueryDSL(memberEmail).ifPresent(member -> toMemberEntity(memberEmail));
        return null;
    }

//    핸드폰 중복검사
    @Override
    public Long checkPhoneNumber(String memberPhoneNumber) {
        return null;
    }

//    비밀번호 찾기
    @Override
    public Long findByMemberPassword(String Email) {
        return null;
    }

//    회원정보 수정
    @Override
    public void updateMember(MemberDTO memberDTO, Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        optionalMember.ifPresent(
                member -> {
                    member.setMemberNickname(memberDTO.getMemberNickname());
                    member.setMemberEmail(memberDTO.getMemberEmail());
                    member.setMemberPhoneNumber(memberDTO.getMemberPhoneNumber());
                    member.setMemberStatus(memberDTO.getMemberStatus());
                    memberRepository.save(member);
                }
        );
    }

    @Override
    public MemberDTO getMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return toMemberDTO(member.get());
    }

    //    뱃지 횟수 조회
    @Override
    public int getMemberBadgeCount(Long id) {
        return memberRepository.findByIdWithAttendCount_QueryDsl(id);
    }

//    뱃지 업데이트
    @Override
    public void updateBadge(Long id) {
        memberRepository.findById(id).ifPresent(member -> {
            int totalCount = memberRepository.findByIdWithAttendCount_QueryDsl(member.getId());

            if (totalCount == 10) {
                member.setBadgeType(BadgeType.ONE);
            } else if (totalCount == 20) {
                member.setBadgeType(BadgeType.TWO);
            } else if (totalCount == 30) {
                member.setBadgeType(BadgeType.THREE);
            }
            memberRepository.save(member);

        });
    }

    @Override
    public Page<MemberDTO> getAllMembersWithPaging(int page) {
        Page<Member> members = memberRepository.findAllByMemberId_QueryDsl(PageRequest.of(page, 10));
        List<MemberDTO> memberDTOS = members.getContent().stream()
                .map(this::toMemberDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(memberDTOS, members.getPageable(), members.getTotalElements());
    }

    @Override
    public void deleteMembers(List<Long> memberIds) {
        for (Long memberId : memberIds) {
            memberRepository.deleteById(memberId);
        }
    }

}

package com.app.projectjar.service.member;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.repository.member.MemberRepository;
import com.app.projectjar.type.BadgeType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface MemberService extends UserDetailsService {
//    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);

//    이메일 중복 검사
    public Long checkEmail(String memberEmail);

//    휴대폰 번호 중복 검사
    public Long checkPhoneNumber(String memberPhoneNumber);

//    비밀번호 찾기
    public Long findByMemberPassword(String Email);

//    회원정보 수정
    public void updateMember(MemberDTO memberDTO, Long id);

//    회원정보 조회
    public MemberDTO getMember(Long id);

//    뱃지 조회
    public int getMemberBadgeCount(Long id);

//    뱃지 업데이트
    public void updateBadge(Long id);


    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .build();
    }

    default MemberFile toMemberFileDTO(MemberFile memberFile){
        return MemberFile.builder()
                .id(memberFile.getId())
                .fileOriginalName(memberFile.getFileOriginalName())
                .fileUuid(memberFile.getFileUuid())
                .filePath(memberFile.getFilePath())
                .fileType(memberFile.getFileType())
                .build();
    }


    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder().
                id(memberDTO.getId())
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

    default MemberFile toMemberFileEntity(FileDTO fileDTO){
        return MemberFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .fileType(fileDTO.getFileType())
                .build();
    }
}

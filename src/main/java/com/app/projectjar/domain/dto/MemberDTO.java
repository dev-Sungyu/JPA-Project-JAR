package com.app.projectjar.domain.dto;

import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @ToString
public class MemberDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberNickname;
    private MemberType memberStatus;
    private BadgeType badgeType;

    private Long fileId;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;


//    @QueryProjection
//    public MemberDTO(String memberEmail,String memberPhoneNumber, String memberName, String memberNickname, String fileOriginalName, String fileUuid, String filePath) {
//        this.memberEmail = memberEmail;
//        this.memberPhoneNumber = memberPhoneNumber;
//        this.memberName = memberName;
//        this.memberNickname = memberNickname;
//        this.fileOriginalName = fileOriginalName;
//        this.fileUuid = fileUuid;
//        this.filePath = filePath;
//    }

    @QueryProjection
    public MemberDTO(Long memberId, String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickname, MemberType memberStatus, BadgeType badgeType) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberStatus = memberStatus;
        this.badgeType = badgeType;
    }


    public Member toEntity(MemberDTO memberDTO) {
        Member member = new Member(
                memberDTO.memberId,
                memberDTO.memberEmail,
                memberDTO.memberPassword,
                memberDTO.memberPhoneNumber,
                memberDTO.memberName,
                memberDTO.memberNickname,
                memberDTO.memberStatus,
                memberDTO.badgeType
        );
        return member;
    }


    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public void setMemberStatus(MemberType memberStatus) {
        this.memberStatus = memberStatus;
    }
}

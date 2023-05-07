package com.app.projectjar.entity.member;

import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class MemberDTO {
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberNickname;
    private MemberType memberStatus;
    private BadgeType badgeType;

    private String fileOriginalName;
    private String fileUuid;
    private String filePath;


    @QueryProjection
    public MemberDTO(String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickname, MemberType memberStatus, BadgeType badgeType, String fileOriginalName, String fileUuid, String filePath) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberStatus = memberStatus;
        this.badgeType = badgeType;
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
}

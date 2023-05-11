package com.app.projectjar.domain.member;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.app.projectjar.type.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberNickname;
    private MemberType memberStatus;
    private BadgeType badgeType;
    private Role memberType;

    private FileDTO fileDTO;

    @Builder
    public MemberDTO(Long id, String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickname, MemberType memberStatus, BadgeType badgeType, Role memberType, FileDTO fileDTO) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberStatus = memberStatus;
        this.badgeType = badgeType;
        this.memberType = memberType;
        this.fileDTO = fileDTO;
    }

    @Builder
    public MemberDTO(Long memberId, String memberEmail, String memberPhoneNumber, String memberName, String memberNickname, MemberType memberStatus, BadgeType badgeType, FileDTO fileDTO) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberStatus = memberStatus;
        this.badgeType = badgeType;
        this.fileDTO = fileDTO;
    }
}

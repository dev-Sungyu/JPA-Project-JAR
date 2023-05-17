package com.app.projectjar.domain.member;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.app.projectjar.type.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
public class MemberDTO implements Serializable {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberNickname;
    private MemberType memberStatus;
    private BadgeType badgeType;
    private Role memberType;
    private LocalDateTime createdDate;

    private FileDTO fileDTO;

    @Builder
    public MemberDTO(Long id, String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickname, MemberType memberStatus, BadgeType badgeType, Role memberType, FileDTO fileDTO, LocalDateTime createdDate) {
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
        this.createdDate = createdDate;
    }

    public MemberDTO(Member member) {
        this.memberEmail = member.getMemberEmail();
        this.memberName = member.getMemberName();
        this.memberNickname = member.getMemberNickname();
        this.memberPhoneNumber = member.getMemberPhoneNumber();
    }
}

package com.app.projectjar.domain.dto.member;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Builder
public class MemberDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberNickname;
    private MemberType memberStatus;
    private BadgeType badgeType;

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

}

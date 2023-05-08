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

    public Member memberToEntity() {
        Member member = new Member(
                this.memberId,
                this.memberEmail,
                this.memberPassword,
                this.memberPhoneNumber,
                this.memberName,
                this.memberNickname,
                this.memberStatus,
                this.badgeType
        );
        return member;
    }

}

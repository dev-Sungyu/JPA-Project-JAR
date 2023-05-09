package com.app.projectjar.domain.member;

import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@NoArgsConstructor
public class MemberDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberNickname;
    private MemberType memberStatus;
    private BadgeType badgeType;

}

package com.app.projectjar.domain.OAuth;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.app.projectjar.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String nickname;
    private final String email;
    private final String mobile;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//        if("naver".equals(registrationId)) {
        return ofNaver("id", attributes);
//        }
//        else {
//            return ofKakao("id", attributes);
//        }
    }


    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .nickname((String) response.get("nickname"))
                .email((String) response.get("email"))
                .mobile((String) response.get("mobile"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberName(name)
                .memberNickname(nickname)
                .memberEmail(email)
                .memberPhoneNumber(mobile)
                .memberType(Role.MEMBER)
                .build();
    }

//    public Member toEntity(){
//        return Member.builder()
//                .memberEmail(email)
//                .memberName(name)
//                .memberNickname(nickname)
//                .memberPhoneNumber(mobile)
//                .memberType(Role.MEMBER)
//                .memberStatus(MemberType.ENABLE)
//                .badgeType(BadgeType.ZERO)
//                .build();
//
//    }
}

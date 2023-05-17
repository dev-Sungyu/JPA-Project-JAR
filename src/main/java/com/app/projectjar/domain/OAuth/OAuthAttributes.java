package com.app.projectjar.domain.OAuth;

import com.app.projectjar.entity.member.Member;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.app.projectjar.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@Slf4j
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String nickname;
    private final String email;
    private final String mobile;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//        if("naver".equals(registrationId)) {
//      userNameAttributeName은 .yml에서 설정해 놓은 user-name-attribute 값이다.
        log.info("================={}", userNameAttributeName);

        //      registrationId는 네이버 로그인일 경우 naver이고 카카오 로그인일 경우 kakao이다.
        log.info("================={}", registrationId);
        return ofNaver(userNameAttributeName, attributes);
//        }
//        else {
//            return ofKakao(userNameAttributeName, attributes);
//        }
    }


    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get(userNameAttributeName);

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .nickname((String) response.get("nickname"))
                .email((String) response.get("email"))
                .mobile((String) response.get("mobile"))
                .attributes(response)
                .nameAttributeKey("id")
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

}

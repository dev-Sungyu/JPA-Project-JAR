package com.app.projectjar.service.member;

import com.app.projectjar.domain.OAuth.OAuthAttributes;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberOAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

        private final MemberRepository memberRepository;
        private final HttpSession session;

        @Override
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
            OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                    .getUserInfoEndpoint().getUserNameAttributeName();
            // naver, kakao 로그인 구분
            OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
            log.info(attributes.getName());
//            log.info(attributes.TZXCWxiqmjgetEmail());
            log.info(attributes.getMobile());
            Member member = saveOrUpdate(attributes);

            session.setAttribute("member", new MemberDTO(member));

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority(member.getMemberType().getSecurityRole())),
                    attributes.getAttributes(),
                    attributes.getNameAttributeKey());
        }

        private Member saveOrUpdate(OAuthAttributes attributes) {
            Member foundMember = memberRepository.findByMemberEmail(attributes.getEmail())
                    .map(member -> member.update(attributes.getName(), attributes.getMobile(), attributes.getEmail(), attributes.getNickname()))
                    .orElse(attributes.toEntity());

            return memberRepository.save(foundMember);
        }
}

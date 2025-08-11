package com.quadra.userservice.application.service;

import com.quadra.userservice.domain.security.oauth.enums.OAuth2Provider;
import com.quadra.userservice.domain.security.oauth.OAuth2UserInfo;
import com.quadra.userservice.domain.security.oauth.OAuth2UserInfoFactory;
import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.enums.UserRole;
import com.quadra.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuadraOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User delegateUser = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Provider provider = OAuth2Provider.valueOf(registrationId.toUpperCase());
        Map<String, Object> attributes = delegateUser.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.of(provider, attributes);

        User user = User.builder()
                .name(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .role(UserRole.USER)
                .build();

        // user 조회
        // 없으면?
        // 있으면?

        String nameAttributeKey = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        for(String key: oAuth2UserInfo.getAttributes().keySet()){
            log.info(key + " : " + oAuth2UserInfo.getAttributes().get(key));
        }
        return delegateUser;
    }
}

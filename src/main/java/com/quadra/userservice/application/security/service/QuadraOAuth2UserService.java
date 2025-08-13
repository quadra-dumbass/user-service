package com.quadra.userservice.application.security.service;

import com.quadra.userservice.application.security.oauth.OAuth2UserInfo;
import com.quadra.userservice.application.security.oauth.OAuth2UserInfoFactory;
import com.quadra.userservice.application.security.oauth.enums.OAuth2Provider;
import com.quadra.userservice.application.security.principal.QuadraOAuth2User;
import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.repository.UserOAuthConnRepository;
import com.quadra.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuadraOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final UserOAuthConnRepository userOAuthConnRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User delegateUser = delegate.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = delegateUser.getAttributes();
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.of(provider, attributes);
        String externalId = oAuth2UserInfo.getExternalId();


        // user oauth conn이 없으면? 회원가입 시켜야하는데 custom exception과 handler 필요할 듯?
        UserOAuthConn userOAuthConn = userOAuthConnRepository.findByProviderAndExternalId(
                        OAuth2Provider.valueOf(provider),
                        externalId)
                .orElseThrow();

        // if user credentials exists and user does NOT,
        // there might be a programmatic or DB-related error.
        User user = userRepository.findById(userOAuthConn.getUserId())
                .orElseThrow();

        return toOAuth2User(user, oAuth2UserInfo);
    }

    private OAuth2User toOAuth2User(User user, OAuth2UserInfo oAuth2UserInfo) {
        return new QuadraOAuth2User(
                user.getId(),
                oAuth2UserInfo.getProvider(),
                Set.of(new SimpleGrantedAuthority(user.getRole().getAuthority())),
                oAuth2UserInfo.getAttributes());
    }
}

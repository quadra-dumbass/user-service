package com.quadra.userservice.application.service;

import com.quadra.userservice.domain.security.oauth.QuadraOAuth2User;
import com.quadra.userservice.domain.security.oauth.QuadraUserDetails;
import com.quadra.userservice.domain.security.oauth.enums.OAuth2Provider;
import com.quadra.userservice.domain.security.oauth.OAuth2UserInfo;
import com.quadra.userservice.domain.security.oauth.OAuth2UserInfoFactory;
import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.enums.UserRole;
import com.quadra.userservice.domain.user.repository.UserOAuthConnRepository;
import com.quadra.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuadraOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final UserOAuthConnRepository userOAuthConnRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // delegate a rest operation to DefaultOAuth2UserService
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User delegateUser = delegate.loadUser(userRequest);

        // get a registration id and an OAuth2UserInfo
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Provider provider = OAuth2Provider.valueOf(registrationId.toUpperCase());
        Map<String, Object> attributes = delegateUser.getAttributes();
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.of(provider, attributes);

        // get a providerUserId
        String providerUserId = oAuth2UserInfo.getProviderUserId();

        // 여기서부터는 user 존재만 가정, 없을 때 처리 필요

        UserOAuthConn conn = userOAuthConnRepository.findByProviderAndProviderUserId(provider, providerUserId)
                .orElseThrow();

        User user = userRepository.findById(conn.getUserId())
                .orElseThrow();

        // 실패했다면, throw에는 어떤 error를 넘기고, 어떻게 핸들링할까?

        return toOAuth2User(user, oAuth2UserInfo);
    }

    // it can be modified if we allow users to have multiple roles.
    // should it be static in case of failure?
    // 이럴 바에 constructor를 만들게 하는 게 낫지 않음?
    private OAuth2User toOAuth2User(User user, OAuth2UserInfo oAuth2UserInfo) {
        String authority = user.getRole().getAuthority();

        return new QuadraOAuth2User(user, Set.of(new SimpleGrantedAuthority(authority)), oAuth2UserInfo.getAttributes());
    }
}

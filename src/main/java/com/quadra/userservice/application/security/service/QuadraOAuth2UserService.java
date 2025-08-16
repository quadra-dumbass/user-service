package com.quadra.userservice.application.security.service;

import com.quadra.userservice.application.security.oauth.OAuth2UserInfo;
import com.quadra.userservice.application.security.oauth.OAuth2UserInfoFactory;
import com.quadra.userservice.application.security.principal.QuadraOAuth2User;
import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.enums.UserRole;
import com.quadra.userservice.domain.user.repository.UserOAuthConnRepository;
import com.quadra.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuadraOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final UserOAuthConnRepository userOAuthConnRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // delegate에서 error가 발생하는 것은 spring security 내부 핸들러가 알아서 처리해줄 것
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User delegateUser = delegate.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = delegateUser.getAttributes();

        // create a OAuth2UserInfo
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.of(provider, attributes);

        log.info("findOrCreateUser start");
        User user = findOrCreateUser(userInfo);

        return toOAuth2User(user, userInfo);
    }

    private User findOrCreateUser(OAuth2UserInfo userInfo) {
        return userOAuthConnRepository
                .findByProviderAndExternalId(userInfo.getProvider(), userInfo.getExternalId())
                .map(conn -> loadExsitingUser(conn.getUserId()))
                .orElseGet(() -> registerNewUser(userInfo));
    }

    private User loadExsitingUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    private User registerNewUser(OAuth2UserInfo userInfo) {
        User newUser = User.builder()
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(newUser);

        UserOAuthConn newConn = UserOAuthConn.builder()
                .userId(savedUser.getId())
                .provider(userInfo.getProvider())
                .externalId(userInfo.getExternalId())
                .build();

        userOAuthConnRepository.save(newConn);
        return savedUser;
    }

    private OAuth2User toOAuth2User(User user, OAuth2UserInfo oAuth2UserInfo) {
        return new QuadraOAuth2User(
                user.getId(),
                oAuth2UserInfo.getProvider(),
                Set.of(new SimpleGrantedAuthority(user.getRole().getAuthority())),
                oAuth2UserInfo.getAttributes());
    }
}
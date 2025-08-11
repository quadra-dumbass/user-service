package com.quadra.userservice.domain.security.oauth;

import com.quadra.userservice.domain.security.oauth.enums.OAuth2Provider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo of(OAuth2Provider provider, Map<String, Object> attributes) {
        return switch (provider) {
            case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
            case KAKAO -> new KakaoOAuth2UserInfo(attributes);
            case NAVER -> new NaverOAuth2UserInfo(attributes);
        };
    }
}

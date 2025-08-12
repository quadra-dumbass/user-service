package com.quadra.userservice.domain.security.oauth;

import java.util.Map;

// To be modified later.
public class KakaoOAuth2UserInfo extends OAuth2UserInfo{

    private final String id;

    @SuppressWarnings("unchecked")
    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("kakao_account"));
        this.id = attributes.get("id").toString();
    }

    @Override
    public String getProviderUserId() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return (String) this.attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) this.attributes.get("name");
    }
}

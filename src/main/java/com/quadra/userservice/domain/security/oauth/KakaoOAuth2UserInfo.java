package com.quadra.userservice.domain.security.oauth;

import java.util.Map;

// To be modified later.
public class KakaoOAuth2UserInfo extends OAuth2UserInfo{

    private String id;

    @SuppressWarnings("unchecked")
    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("kakao_account"));
        this.id = attributes.get("id").toString();
    }

    @Override
    public String getOAuth2Id() {
        return "";
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }
}

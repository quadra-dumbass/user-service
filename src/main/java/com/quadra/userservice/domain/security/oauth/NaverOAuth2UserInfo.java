package com.quadra.userservice.domain.security.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    @SuppressWarnings("unchecked")
    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("response"));
    }

    @Override
    public String getOAuth2Id() {
        return (String) this.attributes.get("id");
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

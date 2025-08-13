package com.quadra.userservice.application.security.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo {

    protected String provider;
    protected Map<String, Object> attributes;

    public abstract String getExternalId();
    public abstract String getEmail();
    public abstract String getName();
}

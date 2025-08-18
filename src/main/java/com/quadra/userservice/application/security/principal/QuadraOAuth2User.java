package com.quadra.userservice.application.security.principal;

import com.quadra.userservice.application.security.oauth.OAuth2UserInfo;
import com.quadra.userservice.domain.user.entity.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

// OAuth 2.0 user information used as a 'Principal' of Authentication (OAuth2AuthenticationToken.class)
@ToString
public class QuadraOAuth2User implements OAuth2User {
    private final Long userId;
    @Getter
    private final String provider;
    private final Set<GrantedAuthority> authorities;
    // not sure whether attributes will be used in a service logic later.
    private final Map<String, Object> attributes;

    // for general purpose (for example, in case you want to set authorities directly.)
    public QuadraOAuth2User(Long userId, String provider, Collection<GrantedAuthority> authorities, Map<String, Object> attributes) {
        this.userId = userId;
        this.provider = provider;
        this.authorities = Set.copyOf(authorities);
        this.attributes = Collections.unmodifiableMap(attributes);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return String.valueOf(this.userId);
    }
}

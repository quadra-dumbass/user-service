package com.quadra.userservice.domain.security.entity;

import com.quadra.userservice.domain.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class UserPrincipal implements UserDetails, OAuth2User {

    private final User user;
    private final Map<String, Object> attributes;

    // for Quadra login
    public UserPrincipal(User user) {
        this(user, Collections.emptyMap());
    }

    // for OAuth2 Login
    public UserPrincipal(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = Collections.unmodifiableMap(new LinkedHashMap<>(attributes));
    }

    // methods below are for Quadra & OAuth2 login

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    // methods below are for OAuth2 login

    // OAuth2 user name
    @Override
    public String getName() {
        return "";
    }

    // OAuth2 attributes
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}

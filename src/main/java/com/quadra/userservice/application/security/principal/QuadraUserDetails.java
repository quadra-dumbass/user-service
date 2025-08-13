package com.quadra.userservice.application.security.principal;

import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Quadra (Not OAuth2) user information used as a 'Principal' of Authentication (UsernamePasswordAuthenticationToken.class)
public class QuadraUserDetails implements UserDetails {
    private final Long userId;
    private final String loginId;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    // for general purpose
    public QuadraUserDetails(Long userId, String loginId, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
        this.authorities = Set.copyOf(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    public String getUserId() {
        return String.valueOf(this.userId);
    }
}

package com.quadra.userservice.domain.security.oauth;

import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QuadraUserDetails implements UserDetails {

    private final User user;
    private final UserCredentials userCredentials;
    private final Set<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(user.getRole().getAuthority()));
    }

    @Override
    public String getPassword() {
        return userCredentials.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}

package com.quadra.userservice.domain.security.oauth;

import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;

import java.util.*;

@RequiredArgsConstructor
// Quadra (Not OAuth2) user information used as a 'Principal' of Authentication (UsernamePasswordAuthenticationToken.class)
public class QuadraUserDetails implements UserDetails {

    private final User user;
    private final UserCredentials userCredentials;
    private final Set<GrantedAuthority> authorities;

    public QuadraUserDetails(User user, UserCredentials userCredentials, Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(user, "User must not be null");
        Assert.notNull(userCredentials, "UserCredentials must not be null");
        this.user = user;
        this.userCredentials = userCredentials;
        this.authorities = Collections.unmodifiableSet(this.hashAuthorities(authorities));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return userCredentials.getPassword();
    }

    // returns loginId if forcePrincipalAsString is set
    @Override
    public String getUsername() {
        return userCredentials.getLoginId();
    }

    private Set<GrantedAuthority> hashAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Authorities must not be null");
        HashSet<GrantedAuthority> hashAuthorities = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority collection must not contain any null elements");
            hashAuthorities.add(grantedAuthority);
        }
        return hashAuthorities;
    }
}

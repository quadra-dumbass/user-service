package com.quadra.userservice.domain.security.oauth;

import com.quadra.userservice.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.Assert;

import java.util.*;

// OAuth 2.0 user information used as a 'Principal' of Authentication (OAuth2AuthenticationToken.class)
public class QuadraOAuth2User implements OAuth2User {
    private final User user;
    private final Set<GrantedAuthority> authorities;
    private final Map<String, Object> attributes;

    public QuadraOAuth2User(User user, OAuth2UserInfo oAuth2UserInfo) {
        this(user, Set.of(new SimpleGrantedAuthority(user.getRole().getAuthority())), oAuth2UserInfo.getAttributes());
    }

    public QuadraOAuth2User(User user, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes) {
        this.user = user;
        this.authorities = Collections.unmodifiableSet(this.hashAuthorities(authorities));
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
        return String.valueOf(this.user.getId());
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

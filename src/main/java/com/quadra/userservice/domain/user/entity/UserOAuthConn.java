package com.quadra.userservice.domain.user.entity;

import com.quadra.userservice.application.security.oauth.enums.OAuth2Provider;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@ToString
// User OAuth information used for OAuth login
public class UserOAuthConn {
    private final Long userId;
    private final OAuth2Provider provider;
    private final String providerUserId;

    @Builder
    public UserOAuthConn(Long userId, OAuth2Provider provider, String providerUserId) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(provider, "provider must not be null");
        Assert.notNull(providerUserId, "providerUserId must not be null");
        this.userId = userId;
        this.provider = provider;
        this.providerUserId = providerUserId;
    }
}

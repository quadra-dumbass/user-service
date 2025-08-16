package com.quadra.userservice.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@ToString
// User OAuth information used for OAuth login
public class UserOAuthConn {
    private final Long userId;
    private final String provider;
    private final String externalId;

    @Builder
    public UserOAuthConn(Long userId, String provider, String externalId) {
        Assert.notNull(provider, "provider must not be null");
        Assert.notNull(externalId, "externalId must not be null");
        this.userId = userId;
        this.provider = provider;
        this.externalId = externalId;
    }
}

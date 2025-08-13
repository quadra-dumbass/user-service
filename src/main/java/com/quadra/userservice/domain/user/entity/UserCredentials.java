package com.quadra.userservice.domain.user.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@ToString
// User credentials used for Quadra login
public class UserCredentials {
    private final Long userId;
    private final String loginId;
    private final String password;
    // private final LocalDateTime LastModified;

    @Builder
    public UserCredentials(Long userId, String loginId, String password) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(password, "password must not be null");
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
    }
}

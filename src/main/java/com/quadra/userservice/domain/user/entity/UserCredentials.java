package com.quadra.userservice.domain.user.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@ToString
// User credentials used for Quadra login
public class UserCredentials {
    private final Long id; // primary key
    private final Long userId; // foreign key
    private final String loginId; // currently, only email is supported.
    private final String password;
    // private final LocalDateTime LastModified;

    @Builder
    public UserCredentials(Long id, Long userId, String loginId, String password) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(password, "password must not be null");
        this.id = id;
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
    }
}

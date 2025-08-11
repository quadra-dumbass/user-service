package com.quadra.userservice.domain.user.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@ToString
@EqualsAndHashCode(of = {"userId", "password"})
public class UserCredentials {
    private final Long id;
    private final Long userId;
    private final String password;
    // private LocalDateTime LastModified;

    @Builder
    public UserCredentials(Long id, Long userId, String password) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(password, "password must not be null");
        this.id = id;
        this.userId = userId;
        this.password = password;
    }
}

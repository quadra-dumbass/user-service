package com.quadra.userservice.domain.user.entity;

import com.quadra.userservice.domain.user.enums.LoginType;
import com.quadra.userservice.domain.user.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode(of = {"id", "email"})
public class User {
    private final Long id;
    private final String name;
    private final String email;
    private final UserRole role;
    // private LoginType loginType;

    @Builder
    public User(Long id, String name, String email, UserRole role) {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(email, "email must not be null");
        this.id = id; // must not be null?
        this.name = name;
        this.email = email;
        this.role = role;
    }
}

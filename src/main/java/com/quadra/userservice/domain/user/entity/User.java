package com.quadra.userservice.domain.user.entity;

import com.quadra.userservice.domain.user.enums.UserRole;
import lombok.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;

@Getter
@ToString
@EqualsAndHashCode(of = {"id", "email"})
// User information excluding credentials
public class User {
    private final Long id; // primary key
    private final String name; // user's name or nickname
    private final String email; // not about credentials, but used as a representative email (for example, when receiving a letter)
    private final UserRole role;
    // private final LoginType loginType;

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

package com.quadra.userservice.infrastructure.repository.user.schema;

import com.quadra.userservice.domain.security.oauth.enums.OAuth2Provider;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user_oauth_conns")
@NoArgsConstructor
@Getter
public class UserOAuthConnSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserSchema user;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private OAuth2Provider provider;

    @Column(name = "provider_user_id", nullable = false)
    private String providerUserId;

    @Builder
    public UserOAuthConnSchema(UserSchema user, OAuth2Provider provider, String providerUserId) {
        this.user = user;
        this.provider = provider;
        this.providerUserId = providerUserId;
    }
}

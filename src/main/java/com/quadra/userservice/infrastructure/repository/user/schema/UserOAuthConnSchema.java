package com.quadra.userservice.infrastructure.repository.user.schema;

import com.quadra.userservice.application.security.oauth.enums.OAuth2Provider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "user_oauth_conns")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

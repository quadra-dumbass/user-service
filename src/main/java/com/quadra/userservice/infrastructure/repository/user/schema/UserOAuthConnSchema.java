package com.quadra.userservice.infrastructure.repository.user.schema;

import com.quadra.userservice.domain.user.enums.OAuth2Provider;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserSchema user;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private OAuth2Provider provider;

    @Column(name = "external_id", nullable = false)
    private String externalId;

    @Builder
    public UserOAuthConnSchema(UserSchema user, OAuth2Provider provider, String externalId) {
        this.user = user;
        this.provider = provider;
        this.externalId = externalId;
    }
}

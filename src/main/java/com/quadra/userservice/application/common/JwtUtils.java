package com.quadra.userservice.application.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final String accessSecret;
    private final String refreshSecret;
    private final int accessExpiresInSeconds;
    private final int refreshExpiresInSeconds;

    @Autowired
    public JwtUtils(
            @Value("${jwt.access.secret}") String accessSecret,
            @Value("${jwt.refresh.secret}") String refreshSecret,
            @Value("${jwt.access.expires}") int accessExpiresInSeconds,
            @Value("${jwt.refresh.expires}") int refreshExpiresInSeconds
    ) {
        this.accessSecret = accessSecret;
        this.refreshSecret = refreshSecret;
        this.accessExpiresInSeconds = accessExpiresInSeconds;
        this.refreshExpiresInSeconds = refreshExpiresInSeconds;
    }

    // jwt header and payload must be modified later
    public String createAccessToken(String userId, String role, String provider, long currentTimeMillis) {

        return JWT.create()
                .withSubject(userId)
                .withClaim("role", role)
                .withClaim("provider", provider)
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + this.accessExpiresInSeconds * 1000L))
                .sign(Algorithm.HMAC256(this.accessSecret));
    }

    // jwt header and payload must be modified later
    public String createRefreshToken(String userId, long currentTimeMillis) {

        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + this.refreshExpiresInSeconds * 1000L))
                .sign(Algorithm.HMAC256(this.refreshSecret));
    }

    public boolean verifyAccessToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(this.accessSecret)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyRefreshToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(this.refreshSecret)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

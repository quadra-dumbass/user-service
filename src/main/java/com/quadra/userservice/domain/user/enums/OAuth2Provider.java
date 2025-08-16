package com.quadra.userservice.domain.user.enums;

import lombok.Getter;

@Getter
public enum OAuth2Provider {
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver");

    private final String value;

    OAuth2Provider(String value) {
        this.value = value;
    }
}

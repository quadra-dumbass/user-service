package com.quadra.userservice.domain.user.enums;

import lombok.Getter;

@Getter
public enum LoginType {
    QUADRA("quadra"),
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver");

    private final String value;

    LoginType(String value) {
        this.value = value;
    }
}

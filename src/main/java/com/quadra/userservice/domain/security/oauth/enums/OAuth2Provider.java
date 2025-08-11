package com.quadra.userservice.domain.security.oauth.enums;

import lombok.Getter;

@Getter
public enum OAuth2Provider {
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver");

    private final String provider;

    OAuth2Provider(String provider){
        this.provider = provider;
    }

}

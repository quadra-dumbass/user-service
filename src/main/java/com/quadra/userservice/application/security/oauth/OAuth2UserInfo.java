package com.quadra.userservice.application.security.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo {

    protected String provider;
    protected Map<String, Object> attributes;

    public abstract String getExternalId();
    public abstract String getEmail();
    public abstract String getName();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("External Id: [");
        sb.append(this.getExternalId());
        sb.append("], Name: [");
        sb.append(this.getProvider());
        sb.append("], Name: [");
        sb.append(this.getName());
        sb.append("], Email: [");
        sb.append(this.getEmail());
        sb.append("], User Attributes: [");
        sb.append(this.getAttributes());
        sb.append("]");
        return sb.toString();
    }
}

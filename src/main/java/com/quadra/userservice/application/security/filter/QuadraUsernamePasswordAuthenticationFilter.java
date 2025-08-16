package com.quadra.userservice.application.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

@Slf4j
public class QuadraUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected QuadraUsernamePasswordAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
}

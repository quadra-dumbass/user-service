package com.quadra.userservice.application.security.handler;

import com.quadra.userservice.application.common.JwtUtils;
import com.quadra.userservice.application.security.principal.QuadraOAuth2User;
import com.quadra.userservice.application.security.principal.QuadraUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuadraLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("QuadraLoginAuthenticationSuccessHandler start");
        Object principal = authentication.getPrincipal();
        String userId;
        boolean isQuadraLogin = principal instanceof QuadraUserDetails;
        if(isQuadraLogin) {
            QuadraUserDetails quadraUserDetails = (QuadraUserDetails) principal;
            userId = quadraUserDetails.getUserId();
        } else {
            QuadraOAuth2User oAuth2User = (QuadraOAuth2User) principal;
            userId = oAuth2User.getName();
        }

        long currentTimeMillis = System.currentTimeMillis();
        String refreshToken = jwtUtils.createRefreshToken(userId, currentTimeMillis);

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/auth/refresh");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(14 * 24 * 60 * 60);
        response.addCookie(cookie);

        response.sendRedirect("/");
    }
}

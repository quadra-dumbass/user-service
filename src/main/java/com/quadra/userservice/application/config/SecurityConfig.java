package com.quadra.userservice.application.config;

import com.quadra.userservice.application.security.handler.OAuth2AuthenticationFailureHandler;
import com.quadra.userservice.application.security.handler.QuadraLoginAuthenticationSuccessHandler;
import com.quadra.userservice.application.security.service.QuadraOAuth2UserService;
import com.quadra.userservice.application.security.service.QuadraUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // each of two classes below must be replaced with your own custom class.
    private final OAuth2AuthenticationFailureHandler oAuth2FailureHandler;
    private final QuadraLoginAuthenticationSuccessHandler loginSuccessHandler;
    private final QuadraOAuth2UserService oAuth2UserService;
    private final QuadraUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // cors policy will be added later.
                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .httpBasic(HttpBasicConfigurer::disable)
                // form login will be disabled and replaced with a custom filter later.
                .formLogin(Customizer.withDefaults())
                // oauth2 client will be modified later, which means that it won't work properly before your own configuration.
                .oauth2Login(oauth -> oauth
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth2/authorization")
                        )
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/oauth2/code/*")
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService)
                        )
                        // .failureHandler(oAuth2FailureHandler)
                        .successHandler(loginSuccessHandler)
                );
        // jwt filter will be added later.

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

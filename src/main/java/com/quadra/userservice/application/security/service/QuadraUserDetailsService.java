package com.quadra.userservice.application.security.service;

import com.quadra.userservice.application.security.principal.QuadraUserDetails;
import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import com.quadra.userservice.domain.user.repository.UserCredentialsRepository;
import com.quadra.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuadraUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserCredentials userCredentials = userCredentialsRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("user credentials for '" + loginId + "' not found"));

        // if user credentials exists and user does NOT,
        // there might be a programmatic or DB-related error.
        User user = userRepository.findById(userCredentials.getUserId())
                .orElseThrow(() -> new IllegalStateException("user not found"));

        return this.toUserDetails(user, userCredentials);
    }

    private UserDetails toUserDetails(User user, UserCredentials userCredentials) {
        return new QuadraUserDetails(user.getId(),
                userCredentials.getLoginId(),
                userCredentials.getPassword(),
                Set.of(new SimpleGrantedAuthority(user.getRole().getAuthority())));
    }
}

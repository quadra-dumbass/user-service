package com.quadra.userservice.application.service;

import com.quadra.userservice.domain.security.oauth.QuadraUserDetails;
import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import com.quadra.userservice.domain.user.enums.UserRole;
import com.quadra.userservice.domain.user.repository.UserCredentialsRepository;
import com.quadra.userservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
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
        UserCredentials credentials = userCredentialsRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("user credentials for '" + loginId + "' not found"));
        User user = userRepository.findById(credentials.getUserId())
                .orElseThrow(() -> new IllegalStateException("user not found"));

        // user 조회 (email과 provider로 조회해야 할 듯) & toDomain
        // 있으면 UserDetails 리턴
        // 없으면 에러 발생 + 핸들러는 어떻게 할까?

        return toUserDetails(user, credentials);
    }

    // it can be modified if we allow users to have multiple roles.
    // 이럴 바에 constructor를 만드는 게 낫지 않음?
    private UserDetails toUserDetails(User user, UserCredentials credentials) {
        String authority = user.getRole().getAuthority();

        return new QuadraUserDetails(user, credentials, Set.of(new SimpleGrantedAuthority(authority)));
    }
}

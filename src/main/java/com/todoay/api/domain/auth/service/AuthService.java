package com.todoay.api.domain.auth.service;

import com.todoay.api.domain.auth.dto.AuthProfileDto;
import com.todoay.api.domain.auth.dto.AuthSaveDto;
import com.todoay.api.domain.auth.entity.Auth;
import com.todoay.api.domain.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;

    // spring security 필수 메소드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    /**
     * 회원정보 저장
     * @return 저장되는 회원의 PK
     **/

    public Long save(AuthProfileDto authProfileDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        authProfileDto.setPassword(encoder.encode(authProfileDto.getPassword()));

        return authRepository.save(authProfileDto.toAuthEntity()).getId();
    }
}

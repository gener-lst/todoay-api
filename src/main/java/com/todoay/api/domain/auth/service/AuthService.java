package com.todoay.api.domain.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

interface AuthService extends UserDetailsService {
    // spring security 필수 메소드
        @Override
        public UserDetails loadUserByUsername(String email);
}

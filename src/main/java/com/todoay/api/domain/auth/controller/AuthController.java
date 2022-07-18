package com.todoay.api.domain.auth.controller;

import com.todoay.api.domain.auth.dto.AuthProfileDto;
import com.todoay.api.domain.auth.repository.AuthRepository;

import com.todoay.api.domain.auth.service.AuthService;
import com.todoay.api.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final ProfileService profileService;

    @PostMapping("save")
    public AuthProfileDto signup(@RequestBody AuthProfileDto authProfileDto) {
        authService.save(authProfileDto);
        profileService.save(authProfileDto);
        return authProfileDto;
    }
}
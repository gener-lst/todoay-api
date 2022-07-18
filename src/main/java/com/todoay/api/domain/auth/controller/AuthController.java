package com.todoay.api.domain.auth.controller;

import com.todoay.api.domain.auth.dto.AuthSaveDto;
import com.todoay.api.domain.auth.repository.AuthRepository;

import com.todoay.api.domain.auth.service.AuthServiceImpl;
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

    private final AuthServiceImpl authServiceImpl;
    private final ProfileService profileService;

    @PostMapping("save")
    public AuthSaveDto signup(@RequestBody AuthSaveDto authSaveDto) {
        authServiceImpl.save(authSaveDto);
        return authSaveDto;
    }
}
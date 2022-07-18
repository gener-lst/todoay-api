package com.todoay.api.global.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    @PostMapping("/reissue")
    public ResponseEntity<JwtDto> reissue(@RequestBody JwtDto tokenDto) {
        return ResponseEntity.ok(jwtService.saveRefreshToken(tokenDto));
    }
}
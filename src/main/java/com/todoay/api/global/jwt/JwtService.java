package com.todoay.api.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtManager jwtManager;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenDto saveRefreshToken(TokenDto tokenDto) {
        Authentication authentication = jwtManager.getAuthentication(tokenDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(RuntimeException::new);

        tokenDto = jwtManager.createLoginToken(authentication);

        if (!refreshToken.getRefreshToken().equals(tokenDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // RefreshToken 저장
        refreshToken = RefreshToken.builder()
                .refreshToken(tokenDto.getRefreshToken())
                .email(tokenDto.getEmail())
                .build();

        RefreshToken newRefreshToken = refreshToken.updateRefreshToken(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}

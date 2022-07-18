package com.todoay.api.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtManager jwtManager;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenDto saveRefreshToken(TokenDto tokenDto) {
        // accessToken에서 인증 정보 가져옴
        Authentication authentication = jwtManager.getAuthentication(tokenDto.getAccessToken());

        // repository에서 refreshToken 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(RuntimeException::new);

        // 인증 정보를 통해 새 토큰 생성
        tokenDto = jwtManager.createLoginToken(authentication.getName());

        // refreshToken이 일치하는지 확인
        if (!refreshToken.getRefreshToken().equals(tokenDto.getRefreshToken())) {
            throw new RuntimeException("토큰 정보가 일치하지 않습니다.");
        }

        // refreshToken 저장
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

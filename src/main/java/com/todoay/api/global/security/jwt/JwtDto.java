package com.todoay.api.global.security.jwt;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class JwtDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String email;
}

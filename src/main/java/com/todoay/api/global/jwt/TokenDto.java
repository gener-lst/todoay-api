package com.todoay.api.global.jwt;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String email;
}

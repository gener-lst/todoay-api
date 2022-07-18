package com.todoay.api.global.security.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// API 요청에 대해 토큰 인증을 실패했을 때에 대한 처리
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // 유효한 자젹증명을 제공하지 않고 접근시 401 ERROR
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
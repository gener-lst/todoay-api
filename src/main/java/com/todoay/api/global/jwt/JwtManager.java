package com.todoay.api.global.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@PropertySource("classpath:secret.properties")
@Component
public class JwtManager {
    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.loginKey}")
    private String loginKey;

    private final long EMAIL_TOKEN_EXPIRATION = 1000 * 60 * 5;
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24; // 하루
    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 30; // 30일
    private static final Logger logger = LoggerFactory.getLogger(JwtManager.class);

    private JwtUserDetailsService jwtUserDetailsService;

    protected void init() {
        loginKey = Base64.getEncoder().encodeToString(loginKey.getBytes());
    }

    public TokenDto createLoginToken(Authentication authentication) {
        Date date = new Date();

        String accessToken =  Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(date)
                .setExpiration(getDateAfter(ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256 ,key)
                .compact();

        String refreshToken =  Jwts.builder()
                .setIssuedAt(date)
                .setExpiration(getDateAfter(REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256 ,key)
                .compact();

        return TokenDto.builder()
                .grantType("bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(authentication.getName())
                .build();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(this.getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(loginKey).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(loginKey).parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e) {
            logger.error("지원되지 않는 토큰입니다.");
        } catch (MalformedJwtException e) {
            logger.error("토큰이 손상되었습니다.");
        } catch (SignatureException e) {
            logger.error("시그니처 검증에 실패하였습니다.");
        } catch (ExpiredJwtException e) {
            logger.error("만료된 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.error("유효하지 않은 토근입니다.");
        }
        return false;
    }

    public String createEmailToken(String email) {
        return Jwts.builder()
                .claim("email", email)
                .setExpiration(getDateAfter(EMAIL_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    private Date getDateAfter(long time) {
        Date date = new Date();
        date.setTime(date.getTime() + time);
        return date;
    }

    public Claims verifyEmailToken(String emailToken) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(emailToken).getBody();
    }
}

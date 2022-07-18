package com.todoay.api.domain.auth.service;

import com.todoay.api.domain.auth.dto.EmailDto;
import com.todoay.api.domain.auth.dto.EmailTokenDto;
import com.todoay.api.domain.auth.utility.MailHandler;
import com.todoay.api.global.security.jwt.JwtManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class MailVerificationService {
    private final JwtManager jwtManager;
    private final JavaMailSender mailSender;

    public void sendVerificationMail(EmailDto emailDto) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(emailDto.getEmail());
            mailHandler.setSubject("[TODOAY] 이메일 인증을 완료해주세요.");
            String emailToken = jwtManager.createEmailToken(emailDto.getEmail());
            mailHandler.setText("<h1>TODOAY</h1></br><p>CODE: " + emailToken +"</p></br>", true);
            mailHandler.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void verifyEmailToken(EmailTokenDto emailTokenDto) {
//         io.jsonwebtoken.UnsupportedJwtException – if the claimsJws argument does not represent an Claims JWS
//         io.jsonwebtoken.MalformedJwtException – if the claimsJws string is not a valid JWS
//         io.jsonwebtoken.SignatureException – if the claimsJws JWS signature validation fails
//         io.jsonwebtoken.ExpiredJwtException – if the specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.
//         IllegalArgumentException – if the claimsJws string is null or empty or only whitespace
        Claims verify = jwtManager.verifyEmailToken(emailTokenDto.getEmailToken());
    }
}

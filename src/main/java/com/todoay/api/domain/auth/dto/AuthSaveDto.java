package com.todoay.api.domain.auth.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthSaveDto {
    private String email;
    private String password;
}

package com.todoay.api.domain.auth.dto;

import com.todoay.api.domain.auth.entity.Auth;
import com.todoay.api.domain.profile.entity.Profile;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthSaveDto {
    private String email;
    private String password;
    private String nickname;
    private String imgUrl;
    private String introMsg;

    public Auth toAuthEntity() {
        Auth authEntity = Auth.builder()
                .email(email)
                .password(password).build();
        authEntity.setProfile(toProfileEntity());
        return authEntity;
    }

    public Profile toProfileEntity() {
        return Profile.builder()
                .nickname(nickname)
                .imgUrl(imgUrl)
                .introMsg(introMsg).build();
    }
}

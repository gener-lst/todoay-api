package com.todoay.api.domain.profile.dto;

import com.todoay.api.domain.auth.entity.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSaveDto {

    private String nickname;
    private String imgUrl;
    private String introMsg;

    private String auth;
}
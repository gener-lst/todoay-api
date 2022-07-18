//package com.todoay.api.domain.auth.dto;
//
//import com.todoay.api.domain.auth.entity.Auth;
//import com.todoay.api.domain.profile.entity.Profile;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AuthProfileDto {
//    private String email;
//    private String password;
//    private String nickname;
//    private String imgUrl;
//    private String introMsg;
//
//    private String auth;
//
//    public Auth toAuthEntity() {
//        return Auth.builder()
//                .email(email)
//                .password(password).build();
//    }
//
//    public Profile toProfileEntity() {
//        return Profile.builder()
//                .nickname(nickname)
//                .imgUrl(imgUrl)
//                .introMsg(introMsg).build();
//    }
//}
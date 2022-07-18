package com.todoay.api.domain.profile.entity;

import com.todoay.api.domain.auth.entity.Auth;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nickname;

    private String imgUrl;

    private String introMsg;

    // Auth table의 PK 참조 (FK)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_Id")
    private Auth auth;

    @Builder  //이게 있으면 쉽게 객체 생성이 가능하다
    public Profile(String nickname, String imgUrl, String introMsg) {
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.introMsg = introMsg;
    }


//    public static Profile saveProfile(ProfileSaveDto profileSaveDto) {
//        // profileEntity타입의 객체를 보내기 위해 profileEntity라는 객체 선언
//       Profile profile = new Profile();
//
//        // profileEntity 객체에 profileSaveDto 객체 안에 담긴 각 요소를 담아줌.
//        profile.setNickname(profileSaveDto.getNickname());
//        profile.setImgUrl(profileSaveDto.getImgUrl());
//        profile.setIntroMsg(profileSaveDto.getIntroMsg());
//
//        // 변환이 완료된 profileEntity 객체를 넘겨줌
//        return profile;
//    }
}
package com.todoay.api.domain.refreshToken.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;

    private String refreshToken;

    @Builder
    public RefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshToken updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}

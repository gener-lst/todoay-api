package com.todoay.api.domain.auth.entity;

import com.todoay.api.domain.profile.entity.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Auth implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY, TABLE, SEQUENCE
    private Long id;

    private String email;

    private String password;

    // Profile table의 PK 참조 (FK)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId")
    private Profile profile;

    @Builder  //이게 있으면 쉽게 객체 생성이 가능하다
    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

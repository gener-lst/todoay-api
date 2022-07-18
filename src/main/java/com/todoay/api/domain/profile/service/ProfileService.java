package com.todoay.api.domain.profile.service;

import com.todoay.api.domain.auth.dto.AuthProfileDto;
import com.todoay.api.domain.profile.dto.ProfileSaveDto;
import com.todoay.api.domain.profile.entity.Profile;
import com.todoay.api.domain.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    // profileService
    // 프로필 저장
    public Long save(AuthProfileDto authProfileDto) {
        return profileRepository.save(authProfileDto.toProfileEntity()).getId();
    }

}
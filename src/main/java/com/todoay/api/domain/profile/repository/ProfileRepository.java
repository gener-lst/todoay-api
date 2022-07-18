package com.todoay.api.domain.profile.repository;

import com.todoay.api.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

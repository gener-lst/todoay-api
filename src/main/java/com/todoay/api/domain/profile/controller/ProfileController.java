package com.todoay.api.domain.profile.controller;

import com.todoay.api.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

}

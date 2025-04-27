package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.dto.request.UpdateUserProfileDTO;
import com.brunoam.CineLog.exceptions.InvalidImageException;
import com.brunoam.CineLog.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PatchMapping("/update")
    public void updateProfile(@Valid @ModelAttribute UpdateUserProfileDTO updateRequest) throws IOException, InvalidImageException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        userProfileService.updateUserProfile(email, updateRequest);
    }
}
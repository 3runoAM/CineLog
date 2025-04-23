package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.exceptions.InvalidImageException;
import com.brunoam.CineLog.service.UserProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PatchMapping("/update")
    public void updateProfile(MultipartFile file, String bio) throws IOException, InvalidImageException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userProfileService.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado para o email: " + authentication.getName()));

        String imagePath = userProfileService.saveProfileImage(file);

        UserProfile updatedUserProfile = UserProfile.builder()
                .id(userProfile.getId())
                .authUser(userProfile.getAuthUser())
                .bio(bio)
                .profileImagePath(imagePath)
                .build();

        userProfileService.saveUserProfile(updatedUserProfile);
    }
}
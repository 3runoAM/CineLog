package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.exceptions.InvalidImageException;
import com.brunoam.CineLog.service.UserProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.IOException;

@RestController
@RequestMapping("profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/new")
    public void updateProfile(MultipartFile file, String bio) throws IOException, InvalidImageException {
        String imagePath = userProfileService.saveProfileImage(file);

        UserProfile userProfile = UserProfile.builder()
                .profileImagePath(imagePath)
                .bio(bio)
                .build();

        userProfileService.saveUserProfile(userProfile);
    }
}
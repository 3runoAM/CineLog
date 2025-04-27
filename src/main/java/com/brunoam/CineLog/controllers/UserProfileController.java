package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.dto.request.UpdateProfileRequestDTO;
import com.brunoam.CineLog.dto.response.UpdateProfileResponseDTO;
import com.brunoam.CineLog.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UpdateProfileResponseDTO> updateProfile(@Valid @ModelAttribute UpdateProfileRequestDTO updateRequest) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UpdateProfileResponseDTO response = userProfileService.updateUserProfile(email, updateRequest);
        return ResponseEntity.ok(response);
    }
}
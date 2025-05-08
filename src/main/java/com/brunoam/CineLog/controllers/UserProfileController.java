package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.dto.request.UpdateProfileRequestDTO;
import com.brunoam.CineLog.dto.response.UserProfileResponseDTO;
import com.brunoam.CineLog.exception.custom.EntityDeletionException;
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

    @GetMapping
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(){
        String email = getAuthenticatedUserEmail();
        UserProfileResponseDTO updateUserProfileInfo = userProfileService.getUserProfile(email);

        return ResponseEntity.ok(updateUserProfileInfo);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserProfileResponseDTO> updateProfile(@Valid @ModelAttribute UpdateProfileRequestDTO updateRequest) throws IOException {
        String email = getAuthenticatedUserEmail();

        UserProfileResponseDTO updateUserProfileInfo = userProfileService.updateUserProfile(email, updateRequest);

        return ResponseEntity.ok(updateUserProfileInfo);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProfile() throws EntityDeletionException {
        String email = getAuthenticatedUserEmail();
        userProfileService.deleteUserProfile(email);

        return ResponseEntity.noContent().build();
    }

    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Requisição não autenticada");

        return email;
    }
}
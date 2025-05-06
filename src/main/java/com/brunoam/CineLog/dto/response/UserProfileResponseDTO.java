package com.brunoam.CineLog.dto.response;

import com.brunoam.CineLog.entities.UserProfile;

public record UserProfileResponseDTO(
        String bio,
        String profileImagePath
) {
    public static UserProfileResponseDTO from(UserProfile userProfile) {
        return new UserProfileResponseDTO(
                userProfile.getBio(),
                userProfile.getProfileImagePath()
        );
    }
}
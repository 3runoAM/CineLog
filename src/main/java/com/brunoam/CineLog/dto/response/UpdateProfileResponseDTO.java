package com.brunoam.CineLog.dto.response;

import com.brunoam.CineLog.entities.UserProfile;

public record UpdateProfileResponseDTO(
        String bio,
        String profileImagePath
) {
    public static UpdateProfileResponseDTO from(UserProfile userProfile) {
        return new UpdateProfileResponseDTO(
                userProfile.getBio(),
                userProfile.getProfileImagePath()
        );
    }
}
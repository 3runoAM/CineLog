package com.brunoam.CineLog.dto.response;

import com.brunoam.CineLog.entities.AuthUser;

public record AuthenticationResponseDTO(
        String token,
        String email,
        String firstName,
        String lastName,
        String profileImagePath
) {
    public static AuthenticationResponseDTO from(String token, AuthUser user) {
        return new AuthenticationResponseDTO(
                token,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserProfile().getProfileImagePath()
        );
    }
}
package com.brunoam.CineLog.dto.response;

import com.brunoam.CineLog.entities.AuthUser;

public record AuthenticationResponseDTO(
        String token,
        String email,
        String firstName,
        String lastName,
        String profileUrl
) {
    public static AuthenticationResponseDTO fromUserAndToken(AuthUser authUser, String token) {
        return new AuthenticationResponseDTO(
                token,
                authUser.getEmail(),
                authUser.getFirstName(),
                authUser.getLastName(),
                authUser.getUserProfile().getProfileImagePath()
        );
    }
}
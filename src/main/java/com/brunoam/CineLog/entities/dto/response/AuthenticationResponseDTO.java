package com.brunoam.CineLog.entities.dto.response;

import com.brunoam.CineLog.entities.User;

public record AuthenticationResponseDTO(
        String token,
        String email,
        String firstName,
        String lastName,
        String profileUrl
) {
    public static AuthenticationResponseDTO fromUserAndToken(User user, String token) {
        return new AuthenticationResponseDTO(
                token,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getProfileUrl()
        );
    }
}
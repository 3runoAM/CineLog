package com.brunoam.CineLog.dto.response;

import com.brunoam.CineLog.entities.AuthUser;
import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.enums.Role;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String email,
        String firstName,
        String lastName,
        Set<Role> roles,
        String bio,
        String profileUrl,
        LocalDateTime createdAt
) {
    public static UserResponseDTO from(AuthUser authUser, UserProfile userProfile) {
        return new UserResponseDTO(
                authUser.getId(),
                authUser.getEmail(),
                authUser.getFirstName(),
                authUser.getLastName(),
                authUser.getRoles(),
                userProfile.getBio(),
                userProfile.getProfileImagePath(),
                authUser.getCreatedAt()
        );
    }
}
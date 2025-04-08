package com.brunoam.CineLog.dto.response;

import com.brunoam.CineLog.entities.User;
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
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles(),
                user.getBio(),
                user.getProfileUrl(),
                user.getCreatedAt()
        );
    }
}
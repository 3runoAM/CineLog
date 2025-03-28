package com.brunoam.CineLog.dto.response.user;

import com.brunoam.CineLog.enums.Role;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String bio,
        String profileImageUrl,
        Set<Role> roles
) {
}
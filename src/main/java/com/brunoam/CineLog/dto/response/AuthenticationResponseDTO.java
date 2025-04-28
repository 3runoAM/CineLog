package com.brunoam.CineLog.dto.response;

import com.brunoam.CineLog.entities.AuthUser;

public record AuthenticationResponseDTO(
        String token,
        String email,
        String firstName,
        String lastName,
        String profileUrl
) { }
package com.brunoam.CineLog.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDTO(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 72) String password) {
}
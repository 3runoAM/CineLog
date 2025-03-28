package com.brunoam.CineLog.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UserRequestDTO(
        @NotBlank @Size(min = 2, max = 20) String firstName,
        @NotBlank @Size(min = 2, max = 20) String lastName,
        @Email String email,
        @NotBlank @Size(min = 8, max = 72) String password,
        @Size(max = 500) String bio,
        @URL String profileImageUrl
) {
}
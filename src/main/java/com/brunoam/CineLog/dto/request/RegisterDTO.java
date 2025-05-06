package com.brunoam.CineLog.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record RegisterDTO(
        @Email @NotBlank String email,
        @NotBlank @Size(min=8, max=16) String password,
        @NotBlank @Size(max = 50)String firstName,
        @NotBlank @Size(max = 50) String lastName,
        @Size(max = 50) String bio,
        @URL String profileUrl
) { }
package com.brunoam.CineLog.dto.request;

import com.brunoam.CineLog.validation.ValidProfileImage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

public record RegisterDTO(
        @Email @NotBlank String email,
        @NotBlank @Size(min=8, max=16) String password,
        @Size(max = 50)String firstName,
        @Size(max = 50) String lastName,
        @Size(max = 50) String bio,
        @ValidProfileImage MultipartFile profileImage
) { }
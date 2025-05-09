package com.brunoam.CineLog.dto.request;

import com.brunoam.CineLog.validation.ValidProfileImage;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record UpdateProfileRequestDTO(
        @NotBlank @Size(max = 50)String firstName,
        @NotBlank @Size(max = 50) String lastName,
        @Length(max = 100) @Column(length = 100) String bio,
        @ValidProfileImage MultipartFile profileImage
) { }
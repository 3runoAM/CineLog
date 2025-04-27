package com.brunoam.CineLog.dto.request;

import com.brunoam.CineLog.Validation.ValidProfileImage;
import jakarta.persistence.Column;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record UpdateUserProfileDTO(
        @Length(max = 100) @Column(length = 100) String bio,
        @ValidProfileImage MultipartFile profileImage
) {
    @Override
    public String bio() {
        return bio;
    }

    @Override
    public MultipartFile profileImage() {
        return profileImage;
    }
}
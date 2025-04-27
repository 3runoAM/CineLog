package com.brunoam.CineLog.services;

import com.brunoam.CineLog.dto.request.UpdateProfileRequestDTO;
import com.brunoam.CineLog.dto.response.UpdateProfileResponseDTO;
import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.repositories.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Value("${app.upload.path}")
    private String uploadPath;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Transactional
    public UpdateProfileResponseDTO updateUserProfile(String email, UpdateProfileRequestDTO userProfileDTO) throws IOException {
        if (userProfileDTO.bio() == null && (userProfileDTO.profileImage() == null || userProfileDTO.profileImage().isEmpty())) {
            throw new IllegalArgumentException("Nenhum dado para atualizar");
        }

        UserProfile userProfile = userProfileRepository.findByAuthUser_Email(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        UserProfile.UserProfileBuilder updatedUserProfileBuilder = userProfile.toBuilder();

        if (userProfileDTO.bio() != null) updatedUserProfileBuilder.bio(userProfileDTO.bio());
        if (userProfileDTO.profileImage() != null && !userProfileDTO.profileImage().isEmpty()) {
            this.deleteExistingProfileImage(userProfile.getProfileImagePath());

            String imagePath = saveProfileImage(userProfileDTO.profileImage());
            updatedUserProfileBuilder.profileImagePath(imagePath);
        }

        UserProfile updatedUserProfile = updatedUserProfileBuilder.build();
        userProfileRepository.save(updatedUserProfile);

        return new UpdateProfileResponseDTO(updatedUserProfile.getBio(), updatedUserProfile.getProfileImagePath());
    }

    private void deleteExistingProfileImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            File existingImage = new File(imagePath);
            if (existingImage.exists()) {
                if (!existingImage.delete()) {
                    throw new RuntimeException("Falha ao excluir a imagem existente: " + imagePath);
                }
            }
        }
    }

    public String saveProfileImage(MultipartFile file) throws IOException {
        String safeFilename = this.generateSafeFilename(file.getOriginalFilename());

        File destinationFile = Paths.get(uploadPath, safeFilename).toFile();

        file.transferTo(destinationFile);
        return destinationFile.toString();
    }

    public String generateSafeFilename(String originalFilename) {
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        return timestamp + "_" + uuid + extension.toLowerCase();
    }
}

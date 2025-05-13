package com.brunoam.CineLog.services;

import com.brunoam.CineLog.dto.request.RegisterDTO;
import com.brunoam.CineLog.dto.request.UpdateProfileRequestDTO;
import com.brunoam.CineLog.dto.response.UserProfileResponseDTO;
import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.exception.custom.EntityDeletionException;
import com.brunoam.CineLog.exception.custom.ImageDeletionException;
import com.brunoam.CineLog.exception.custom.UserProfileNotFound;
import com.brunoam.CineLog.repositories.UserProfileRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
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

    // get
    public UserProfileResponseDTO getUserProfile(String username) {
        UserProfile userProfile = userProfileRepository.findByAuthUser_Email(username)
                .orElseThrow(() -> new UserProfileNotFound("Perfil não encontrado para o email: " + username));

        return UserProfileResponseDTO.from(userProfile);
    }

    // post
    public UserProfile createUserProfile(RegisterDTO userData) throws IOException {
        if(userProfileRepository.existsByAuthUser_Email(userData.email())) throw new IllegalArgumentException("Email já possui um perfil");

        String imagePath = saveProfileImage(userData.profileImage());

        UserProfile userProfile = UserProfile.builder()
                .firstName(userData.firstName())
                .lastName(userData.lastName())
                .bio(userData.bio())
                .profileImagePath(imagePath)
                .build();

        return userProfileRepository.save(userProfile);
    }

    // patch
    @Transactional
    public UserProfileResponseDTO updateUserProfile(String username, UpdateProfileRequestDTO userProfileDTO) throws IOException {
        UserProfile userProfile = userProfileRepository.findByAuthUser_Email(username)
                .orElseThrow(() -> new UserProfileNotFound("Perfil não encontrado para o email: " + username));

        UserProfile.UserProfileBuilder updateUserProfileBuilder = userProfile.toBuilder();

        if (userProfileDTO.bio() != null) updateUserProfileBuilder.bio(userProfileDTO.bio());
        if (userProfileDTO.profileImage() != null && !userProfileDTO.profileImage().isEmpty()) {
            this.deleteExistingProfileImage(userProfile.getProfileImagePath());

            String imagePath = saveProfileImage(userProfileDTO.profileImage());
            updateUserProfileBuilder.profileImagePath(imagePath);
        }

        if (userProfileDTO.firstName() != null && !userProfileDTO.firstName().isBlank()) updateUserProfileBuilder.firstName(userProfileDTO.firstName());

        UserProfile updatedUserProfile = updateUserProfileBuilder.build();
        userProfileRepository.save(updatedUserProfile);

        return UserProfileResponseDTO.from(updatedUserProfile);
    }

    // delete
    public void deleteUserProfile(String username) throws EntityDeletionException {
        UserProfile userProfile = userProfileRepository.findByAuthUser_Email(username)
                .orElseThrow(() -> new UserProfileNotFound("Perfil não encontrado para o email: " + username));

        this.deleteExistingProfileImage(userProfile.getProfileImagePath());
        userProfileRepository.delete(userProfile);

        if (userProfileRepository.existsById(userProfile.getId())) {
            throw new EntityDeletionException("Falha ao excluir o perfil do usuário: " + username);
        }
    }

    private void deleteExistingProfileImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            File existingImage = new File(imagePath);
            if (existingImage.exists()) {
                if (!existingImage.delete()) {
                    throw new ImageDeletionException("Falha ao excluir a imagem existente: " + imagePath);
                }
            }
        }
    }

    private String saveProfileImage(MultipartFile file) throws IOException {
        String safeFilename = this.generateSafeFilename(file.getOriginalFilename());

        File destinationFile = Paths.get(uploadPath, safeFilename).toFile();

        file.transferTo(destinationFile);
        return destinationFile.toString();
    }

    private String generateSafeFilename(String originalFilename) {
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        return timestamp + "_" + uuid + extension.toLowerCase();
    }
}

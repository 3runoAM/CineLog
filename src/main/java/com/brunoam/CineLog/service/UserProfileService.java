package com.brunoam.CineLog.service;

import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.exceptions.InvalidImageException;
import com.brunoam.CineLog.repositories.UserProfileRepository;
import com.brunoam.CineLog.utils.ImageValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final ImageValidator profilePicValidator;

    @Value("${app.upload.path}")
    private String uploadPath;

    public UserProfileService(UserProfileRepository userProfileRepository, ImageValidator profilePicValidator) {
        this.userProfileRepository = userProfileRepository;
        this.profilePicValidator = profilePicValidator;
    }

    public void saveUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    public Optional<UserProfile> findByEmail(String email) {
        return userProfileRepository.findByAuthUser_Email(email);
    }

    /**
     * Valida e salva a imagem de perfil do usuário no servidor.
     *
     * @param file O arquivo da imagem a ser salvo.
     * @return O caminho completo do arquivo salvo.
     */
    public String saveProfileImage(MultipartFile file) throws IOException, InvalidImageException {
        profilePicValidator.validate(file);

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

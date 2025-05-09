package com.brunoam.CineLog.services;

import com.brunoam.CineLog.dto.request.RegisterDTO;
import com.brunoam.CineLog.dto.response.UserProfileResponseDTO;
import com.brunoam.CineLog.dto.response.UserResponseDTO;
import com.brunoam.CineLog.entities.AuthUser;
import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.enums.Role;
import com.brunoam.CineLog.repositories.UserProfileRepository;
import com.brunoam.CineLog.repositories.AuthUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserRegistrationService {
    private final AuthUserRepository authUserRepository;
    private final UserProfileService userProfileService;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(AuthUserRepository authUserRepository, UserProfileService userProfileService, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.userProfileService = userProfileService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUser findByEmail(String email) {
        return authUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean existsByEmail(String email) {
        return authUserRepository.existsByEmail(email);
    }

    public UserResponseDTO registerUser(RegisterDTO userData) throws IOException {
        String encryptedPassword = passwordEncoder.encode(userData.password());

        UserProfile userProfile = userProfileService.createUserProfile(userData);

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);

        AuthUser authUser = AuthUser.builder()
                .email(userData.email())
                .hashPassword(encryptedPassword)
                .roles(roles)
                .userProfile(userProfile)
                .build();

        AuthUser savedUser = authUserRepository.save(authUser);

        return UserResponseDTO.from(savedUser);
    }
}
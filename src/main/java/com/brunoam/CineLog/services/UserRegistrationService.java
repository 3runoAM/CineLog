package com.brunoam.CineLog.services;

import com.brunoam.CineLog.dto.request.RegisterDTO;
import com.brunoam.CineLog.entities.AuthUser;
import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.enums.Role;
import com.brunoam.CineLog.repositories.UserProfileRepository;
import com.brunoam.CineLog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUser findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public AuthUser registerUser(RegisterDTO userData){
        String encryptedPassword = passwordEncoder.encode(userData.password());

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);

        AuthUser newAuthUser = AuthUser.builder()
                .email(userData.email())
                .hashPassword(encryptedPassword)
                .firstName(userData.firstName())
                .lastName(userData.lastName())
                .roles(roles)
                .build();

        return userRepository.save(newAuthUser);
    }

    public UserProfile registerUserProfile(AuthUser savedAuthUser){
        UserProfile newUserProfile = UserProfile.builder()
                .authUser(savedAuthUser)
                .build();

        return userProfileRepository.save(newUserProfile);
    }
}

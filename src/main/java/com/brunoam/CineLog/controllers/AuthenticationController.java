package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.entities.User;
import com.brunoam.CineLog.entities.dto.request.RegisterDTO;
import com.brunoam.CineLog.enums.Role;
import com.brunoam.CineLog.repositories.UserRepository;
import com.brunoam.CineLog.service.JwtService;
import com.brunoam.CineLog.entities.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterDTO data) {
        if (userRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);

        User newUser = User.builder()
                .email(data.email())
                .hashPassword(encryptedPassword)
                .firstName(data.firstName())
                .lastName(data.lastName())
                .bio(data.bio())
                .profileUrl(data.profileUrl())
                .roles(roles)
                .build();

        User savedUser = userRepository.save(newUser);

        return ResponseEntity.ok(UserResponseDTO.fromEntity(savedUser));
    }
}
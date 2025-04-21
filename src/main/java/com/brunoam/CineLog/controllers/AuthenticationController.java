package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.entities.AuthUser;
import com.brunoam.CineLog.dto.request.AuthenticationRequestDTO;
import com.brunoam.CineLog.dto.request.RegisterDTO;
import com.brunoam.CineLog.dto.response.AuthenticationResponseDTO;
import com.brunoam.CineLog.enums.Role;
import com.brunoam.CineLog.repositories.UserRepository;
import com.brunoam.CineLog.service.JwtService;
import com.brunoam.CineLog.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService tokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO userData){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userData.email(), userData.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserDetails) auth.getPrincipal());
        var user = userRepository.findByEmail(userData.email()).orElseThrow();

        return ResponseEntity.ok(AuthenticationResponseDTO.fromUserAndToken(user, token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterDTO userData) {
        if (userRepository.findByEmail(userData.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

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

        AuthUser savedAuthUser = userRepository.save(newAuthUser);

        return ResponseEntity.ok(UserResponseDTO.fromEntity(savedAuthUser));
    }
}
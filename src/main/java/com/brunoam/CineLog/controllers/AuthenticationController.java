package com.brunoam.CineLog.controllers;

import com.brunoam.CineLog.entities.AuthUser;
import com.brunoam.CineLog.dto.request.AuthenticationRequestDTO;
import com.brunoam.CineLog.dto.request.RegisterDTO;
import com.brunoam.CineLog.dto.response.AuthenticationResponseDTO;
import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.services.JwtService;
import com.brunoam.CineLog.dto.response.UserResponseDTO;
import com.brunoam.CineLog.services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final JwtService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRegistrationService userRegistrationService;

    public AuthenticationController(JwtService tokenService,
                                    AuthenticationManager authenticationManager,
                                    UserRegistrationService userRegistrationService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO userData){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(userData.email(), userData.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((UserDetails) auth.getPrincipal());
        AuthUser user = userRegistrationService.findByEmail(userData.email());

        return ResponseEntity.ok(AuthenticationResponseDTO.from(token, user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterDTO userData) {
        if (userRegistrationService.existsByEmail(userData.email())) return ResponseEntity.badRequest().build();

        AuthUser savedAuthUser = userRegistrationService.registerUser(userData);
        UserProfile savedUserProfile = userRegistrationService.registerUserProfile(savedAuthUser);

        return ResponseEntity.ok(UserResponseDTO.from(savedAuthUser, savedUserProfile));
    }
}
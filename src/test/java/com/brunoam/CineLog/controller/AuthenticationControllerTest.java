package com.brunoam.CineLog.controller;

import com.brunoam.CineLog.controllers.AuthenticationController;
import com.brunoam.CineLog.dto.request.RegisterDTO;
import com.brunoam.CineLog.entities.AuthUser;
import com.brunoam.CineLog.entities.UserProfile;
import com.brunoam.CineLog.enums.Role;
import com.brunoam.CineLog.repositories.AuthUserRepository;
import com.brunoam.CineLog.repositories.UserProfileRepository;
import com.brunoam.CineLog.security.SecurityConfig;
import com.brunoam.CineLog.services.JwtService;
import com.brunoam.CineLog.services.UserRegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(AuthenticationController.class)
@Import({SecurityConfig.class, UserRegistrationService.class})
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc; // Simula requisições HTTP para o controlador

    @MockitoBean
    private JwtService tokenService;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private AuthUserRepository userRepository;

    @MockitoBean
    private UserProfileRepository userProfileRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private UserRegistrationService userRegistrationService;

    @Test
    public void shouldReturnOkWhenRegistering() throws Exception {
        UserProfile profile = UserProfile.builder().id(UUID.fromString("b06e7167-38e4-4406-998c-6778aa9ae02a"))
                .bio("Engenheiro de software com 5 anos")
                .profileImagePath("https://example.com/profile.jpg")
                .build();

        AuthUser auth = AuthUser.builder()
                .id(UUID.fromString( "9ff7cf04-8baf-4735-a4d2-e118569760b2"))
                .email("joao.silva@exemplo.com")
                .hashPassword("senhaSegura123")
                .firstName("joao")
                .lastName("Silva")
                .roles(Set.of(Role.ROLE_USER))
                .userProfile(profile)
                .build();

        when(userRegistrationService.existsByEmail(ArgumentMatchers.anyString())).thenReturn(false);
        when(userRegistrationService.registerUser(any(RegisterDTO.class))).thenReturn(auth);
        when(userRegistrationService.registerUserProfile(any(AuthUser.class))).thenReturn(profile);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "email": "joao.silva@exemplo.com",
                                        "password": "senhaSegura123",
                                        "firstName": "joao",
                                        "lastName": "Silva",
                                        "bio": "Engenheiro de software com 5 anos de experiência",
                                        "profileUrl": "https://linkedin.com/in/joaoilva"
                                    }
                                """))
                .andExpect(status().isOk());
    }
}
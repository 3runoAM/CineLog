package com.brunoam.CineLog.services.auth;

import com.brunoam.CineLog.repositories.AuthUserRepository;
import com.brunoam.CineLog.security.auth.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brunoam.CineLog.entities.AuthUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthUserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthUser authUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return UserDetailsImpl.builder()
                .authUser(authUser)
                .build();
    }
}
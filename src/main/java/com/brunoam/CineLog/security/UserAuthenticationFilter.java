package com.brunoam.CineLog.security;

import com.brunoam.CineLog.repositories.AuthUserRepository;
import com.brunoam.CineLog.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtTokenService;
    private final AuthUserRepository userRepository;

    @Autowired
    public UserAuthenticationFilter(JwtService jwtTokenService, AuthUserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            String username = jwtTokenService.getUsername(token);
            userRepository.findByEmail(username).ifPresent(user -> {
                UserDetails userDetails = UserDetailsImpl.builder().authUser(user).build();

                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }
}
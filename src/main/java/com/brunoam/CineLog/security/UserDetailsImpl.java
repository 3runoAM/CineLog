package com.brunoam.CineLog.security;

import com.brunoam.CineLog.entities.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


@Builder
public class UserDetailsImpl implements UserDetails {
    private final User user;

    /**
     * Retorna as autoridades (permissões) concedidas ao usuário.
     *
     * @return uma coleção de GrantedAuthority que representam as permissões do usuário.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return a senha hash do usuário
     */
    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    /**
     * Retorna o username, no caso o email do usuário.
     *
     * @return o username do usuário
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
    * Retorna o nome completo do usuário.
    *
    * @return o nome completo do usuário
    */
    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    /**
     * Retorna a bio do usuário.
     *
     * @return a bio do usuário
     */
    public String getBio() {
        return user.getBio();
    }

    /**
     * Retorna a uri da foto de perfil do usuário.
     *
     * @return a uri da foto de perfil do usuário
     */
    public String getProfileUrl() {
        return user.getProfileUrl();
    }
}

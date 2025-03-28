package com.brunoam.CineLog.entities;

import com.brunoam.CineLog.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.Set;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 1, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 20)
    private String lastName;

    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    @Column(unique = true)
    private String email;

    private String passwordHash;

    @Size(max = 500)
    private String bio;

    @URL
    private String profileImageUrl;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany
    private List<Review> reviews;

    @OneToMany
    private List<com.brunoam.CineLog.entities.List> lists;

    @OneToMany
    private List<Like> likes;

    @OneToMany
    private List<Comment> comments;

    @OneToMany
    private List<Notification> notifications;
}
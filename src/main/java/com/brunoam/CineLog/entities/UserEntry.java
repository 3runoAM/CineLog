package com.brunoam.CineLog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntry extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    @NotNull
    @ManyToOne
    private AuthUser authUser;

    @NotNull
    @ManyToOne
    private Movie movie;

    @DecimalMin("0.5")
    @DecimalMax("5.0")
    private Double rating;

    @Length(max = 2000)
    @Column(length = 2000)
    private String reviewText;

    @ElementCollection
    private List<String> tags;

    private boolean watched;

    private boolean favorite;
}

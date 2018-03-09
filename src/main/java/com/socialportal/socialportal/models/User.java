package com.socialportal.socialportal.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Size(min = 4, max = 30)
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @Transient
    private String confirmPassword;
}

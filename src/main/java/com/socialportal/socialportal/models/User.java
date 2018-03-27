package com.socialportal.socialportal.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 6, max = 60)
    private String password;

    @NotNull
    @Column(unique = true)
    private String username;

    @Transient
    private String confirmPassword;


    public User(@NotNull @Size(min = 3, max = 30) String firstName, @NotNull @Size(min = 3, max = 30) String lastName, @NotNull @Size(min = 6, max = 60) String password, @NotNull String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

}

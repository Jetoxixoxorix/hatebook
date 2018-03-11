package com.socialportal.socialportal.models;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Data
@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String email;

    @Transient
    private String confirmPassword;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

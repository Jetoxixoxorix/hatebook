package com.socialportal.socialportal.models;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String content;

    private Long UserId;

}

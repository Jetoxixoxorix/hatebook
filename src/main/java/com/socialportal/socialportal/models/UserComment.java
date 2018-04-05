package com.socialportal.socialportal.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long commentId;

    private String content;

    private Date date;

    private Long userId;

    @NotNull
    @ManyToOne
    @JoinColumn
    private UserStatus userStatus;
}

package com.socialportal.socialportal.models;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tab")
    @Setter(AccessLevel.NONE)
    @Column(name = "status_id")
    private Long statusId;

    @NotNull
    @Size(max = 3000)
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn
    private User addingUser;

    public UserStatus(String content, Long userId, Date date, User addingUser) {
        this.content = content;
        this.userId = userId;
        this.date = date;
        this.addingUser = addingUser;
    }
}

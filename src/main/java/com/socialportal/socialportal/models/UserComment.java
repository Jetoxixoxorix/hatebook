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
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tab")
    @Setter(AccessLevel.NONE)
    private Long commentId;

    @NotNull
    @Size(max = 3000)
    private String content;

    @NotNull
    private Date date;

    @NotNull
    private Long userId;

    @NotNull
    @ManyToOne
    @JoinColumn
    private UserStatus userStatus;

    @NotNull
    @ManyToOne
    @JoinColumn
    private User addingUser;

    public UserComment(@NotNull String content, @NotNull Date date, @NotNull Long userId, @NotNull UserStatus userStatus, @NotNull User addingUser) {
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.userStatus = userStatus;
        this.addingUser = addingUser;
    }
}

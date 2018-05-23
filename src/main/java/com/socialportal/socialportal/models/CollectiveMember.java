package com.socialportal.socialportal.models;

import javafx.scene.Group;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class CollectiveMember {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tab")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Collective group;

    @ManyToOne
    @JoinColumn
    @NotNull
    private User user;

    @NotNull
    private boolean isAdmin;

    public CollectiveMember(@NotNull Collective group, @NotNull User user, @NotNull boolean isAdmin) {
        this.group = group;
        this.user = user;
        this.isAdmin = isAdmin;
    }
}

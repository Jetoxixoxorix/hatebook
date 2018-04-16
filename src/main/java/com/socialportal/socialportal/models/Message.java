package com.socialportal.socialportal.models;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tab")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn
    private User receiver;

    @NotNull
    @ManyToOne
    @JoinColumn
    private User sender;
}

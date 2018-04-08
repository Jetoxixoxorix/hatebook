package com.socialportal.socialportal.models;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn
    private User friend;
}

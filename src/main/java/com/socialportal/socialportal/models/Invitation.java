package com.socialportal.socialportal.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    
    @ManyToOne
    @JoinColumn
    private User receiver;

    @ManyToOne
    @JoinColumn
    private User sendUser;
}

package com.heartcooking.auth.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles", schema = "heartcooking")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }


}

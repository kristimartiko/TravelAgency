package com.example.TravelAgency.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long roleId;

    @Column(nullable = false, name = "name")
    @Getter @Setter
    private String name;

}

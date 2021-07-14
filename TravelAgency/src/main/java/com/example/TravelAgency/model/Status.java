package com.example.TravelAgency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long status_id;

    @Column(nullable = false, name = "name")
    @Getter @Setter
    private String name;
}

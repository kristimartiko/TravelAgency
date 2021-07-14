package com.example.TravelAgency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long userId;

    @Column(nullable = false, name = "first_name")
    @Getter @Setter
    private String firstName;

    @Column(nullable = false, name = "last_name")
    @Getter @Setter
    private String lastName;

    @Column(nullable = false, name = "email")
    @Getter @Setter
    private String email;

    @Column(nullable = false, name = "password")
    @Getter @Setter
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
    @Setter @Getter
    private Set<Role> roles;



}

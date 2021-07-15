package com.example.TravelAgency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
}

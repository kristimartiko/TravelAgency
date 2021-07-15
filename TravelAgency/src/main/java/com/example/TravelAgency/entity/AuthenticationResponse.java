package com.example.TravelAgency.entity;

import lombok.Getter;
import lombok.Setter;

public class AuthenticationResponse {
    @Getter @Setter
    private final String jwt;
    @Getter @Setter
    private final String firstName;

    public AuthenticationResponse(String jwt, String firstName) {
        this.jwt = jwt;
        this.firstName = firstName;
    }
}

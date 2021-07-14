package com.example.TravelAgency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

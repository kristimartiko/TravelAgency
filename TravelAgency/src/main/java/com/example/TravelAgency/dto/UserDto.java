package com.example.TravelAgency.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class UserDto implements Serializable {
    @Getter
    public String firstName;
    @Getter
    public String lastName;
    @Getter
    public String email;
    @Getter
    public String password;
}

package com.example.TravelAgency.dto;

import com.example.TravelAgency.model.User;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

public class TripDto implements Serializable {
    @Getter
    public String tripReason;
    @Getter
    public String tripDescription;
    @Getter
    public String fromPlace;
    @Getter
    public String toPlace;
    @Getter
    public Date departureDate;
    @Getter
    public Date arrivalDate;
    @Getter
    public User user;
    @Getter
    public String status;
}

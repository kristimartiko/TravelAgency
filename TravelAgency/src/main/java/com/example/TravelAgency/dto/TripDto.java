package com.example.TravelAgency.dto;

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
    public String status;
}

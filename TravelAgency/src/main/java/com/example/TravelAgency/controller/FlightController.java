package com.example.TravelAgency.controller;

import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.entity.FlightEntity;
import com.example.TravelAgency.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping(value = "/addFlight{id}")
    public FlightEntity addFlight(@PathVariable("id") Long id) {
        return flightService.addFlight(id);
    }

    @GetMapping(value = "getFlights")
    public List<FlightEntity> getFlights() {
        return flightService.getAllFlights();
    }
}

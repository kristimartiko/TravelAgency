package com.example.TravelAgency.controller;

import com.example.TravelAgency.entity.FlightEntity;
import com.example.TravelAgency.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;

@RestController
@CrossOrigin
public class FlightController {

    private static final Logger logger = Logger.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;

    @PostMapping(value = "/addFlight{id}")
    public FlightEntity addFlight(@PathVariable("id") Long id) {
        logger.info("FlightController: Added flight with id" + id);
        return flightService.addFlight(id);
    }

    @GetMapping(value = "getFlights")
    public List<FlightEntity> getFlights() {
        logger.info("FlightController: Fetching all the flights");
        return flightService.getAllFlights();
    }
}

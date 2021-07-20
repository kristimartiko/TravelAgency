package com.example.TravelAgency.controller;

import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.entity.TripEntity;
import com.example.TravelAgency.service.TripService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class TripController {
    private static final Logger logger = Logger.getLogger(TripController.class);

    @Autowired
    private TripService tripService;

    @PostMapping("/createTrip")
    public TripEntity createTrip(@RequestBody TripDto tripDto) {
        logger.info("TripController: Creating a new trip.");
        return tripService.createTrip(tripDto);
    }

    @PutMapping("/updateTrip{id}")
    public void updateTrip(@RequestBody TripDto tripDto,
                           @PathVariable("id") Long id) {
        logger.info("TripController: Updating trip with id: " + id);
        tripService.updateTrip(tripDto, id);

    }

    @DeleteMapping("/deleteTrip{id}")
    public void deleteTrip(@PathVariable("id") Long id) {
        logger.info("TripController: Deleting trip with id: " + id);
        tripService.deleteTrip(id);
    }

    @GetMapping("/trips")
    public List<TripEntity> getTrips() {
       logger.info("TripController: Fetching all trips.");
        return tripService.getTrips();
    }

    @GetMapping("/pendingTrips")
    public List<TripEntity> getPendingTrips() {
        logger.info("TripController: Fetching all pending trips.");
        return tripService.getPendingTrips();
    }

    @GetMapping("/sendApproval{id}")
    public TripEntity sendApproval(@PathVariable("id") Long id) {
        logger.info("TripController: Changing status to Pending for trip with id: " + id);
        return tripService.sendApproval(id);
    }

    @GetMapping("/approveTrip{id}")
    public TripEntity approveTrip(@PathVariable("id") Long id) {
        logger.info("TripController: Changing status to Approved for trip with id: " +id);
        return tripService.approveTrip(id);
    }

    @GetMapping("/declineTrip{id}")
    public TripEntity declineTrip(@PathVariable("id") Long id) {
        logger.info("TripController: Decline trip with id: " +id);
        return tripService.declineTrip(id);
    }

}

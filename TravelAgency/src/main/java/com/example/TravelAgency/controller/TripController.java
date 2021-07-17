package com.example.TravelAgency.controller;

import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.entity.TripEntity;
import com.example.TravelAgency.enumeration.TripStatusEnum;
import com.example.TravelAgency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TripController {
    @Autowired
    private TripService tripService;

    @PostMapping("/createTrip")
    public TripEntity createTrip(@RequestBody TripDto tripDto) {
        return tripService.createTrip(tripDto);
    }

    @PutMapping("/updateTrip{id}")
    public void updateTrip(@RequestBody TripDto tripDto,
                           @PathVariable("id") Long id) {
        tripService.updateTrip(tripDto, id);
    }

    @DeleteMapping("/deleteTrip{id}")
    public void deleteTrip(@PathVariable("id") Long id) {
        tripService.deleteTrip(id);
    }

    @GetMapping("/trips")
    public List<TripEntity> getTrips() {
       return tripService.getTrips();
    }

    @GetMapping("/pendingTrips")
    public List<TripEntity> getPendingTrips() {
        return tripService.getPendingTrips();
    }

    @GetMapping("/approvedTrips")
    public List<TripEntity> getApprovedTrips() {
        return tripService.getApprovedTrips();
    }

    @PostMapping("/sendApproval{id}")
    public TripEntity sendApproval(@PathVariable("id") Long id) {
        return tripService.sendApproval(id);
    }

    @PostMapping("/approveTrip{id}")
    public TripEntity approveTrip(@PathVariable("id") Long id) {
        return tripService.approveTrip(id);
    }

    @PostMapping("/declineTrip{id}")
    public TripEntity declineTrip(@PathVariable("id") Long id) {
        return tripService.declineTrip(id);
    }

    @GetMapping("/status{id}")
    public TripStatusEnum getStatus(@PathVariable("id") Long id) {
        return tripService.getStatus(id);
    }

}

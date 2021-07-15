package com.example.TravelAgency.service;

import com.example.TravelAgency.model.Trip;
import com.example.TravelAgency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public Trip sendApproval(Long trip_id) {
        Trip trip = tripRepository.isPresent(trip_id);
        tripRepository.sendApprove(trip.getTripId());
        return tripRepository.save(trip);
    }

    public Trip approve(Long trip_id) {
        Trip trip = tripRepository.isPresent(trip_id);
        tripRepository.approve(trip.getTripId());
        return tripRepository.save(trip);
    }

    public void disaprove(Long trip_id) {
        Trip trip = tripRepository.isPresent(trip_id);
        tripRepository.deleteTripById(trip.getTripId());
    }

    public List<Trip> getTrips() {
        return tripRepository.tripsCreated();
    }

    public List<Trip> getTripsSendForApproval() {
        return tripRepository.tripsSendForApproval();
    }

    public List<Trip> getApprovedTrips() {
        return tripRepository.tripsApproved();
    }
}

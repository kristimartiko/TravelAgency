package com.example.TravelAgency.service;

import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.enumeration.TripStatusEnum;
import com.example.TravelAgency.entity.Trip;
import com.example.TravelAgency.entity.User;
import com.example.TravelAgency.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {

    private final TripRepository tripRepository;

    private final MyUserDetailService myUserDetailService;

    public Trip createTrip(TripDto tripDto) {
        User user = myUserDetailService.getCurrentUser();
        Trip trip = Trip.builder()
                .tripReason(tripDto.getTripReason())
                .tripDescription(tripDto.getTripDescription())
                .fromPlace(tripDto.getFromPlace())
                .toPlace(tripDto.getToPlace())
                .departureDate(tripDto.getDepartureDate())
                .arrivalDate(tripDto.getArrivalDate())
                .user(user)
                .status(TripStatusEnum.CREATED)
                .build();
        return tripRepository.save(trip);
    }

    public void updateTrip(TripDto tripDto, Long tripId) {
//        Trip trip = tripRepository.isPresent(trip_id);
//        trip.setTripReason(tripDto.getTripReason());
//        trip.setTripDescription(tripDto.getTripDescription());
//        trip.setFromPlace(tripDto.getFromPlace());
//        trip.setToPlace(tripDto.getToPlace());
//        trip.setDepartureDate(tripDto.getDepartureDate());
//        trip.setArrivalDate(tripDto.getArrivalDate());
//        tripRepository.save(trip);
    }

    public void deleteTrip(Long tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            tripRepository.deleteTripById(tripId);
        }
    }

    public Trip sendApproval(Long tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if (trip.isPresent()) {
            trip.get().setStatus(TripStatusEnum.PENDING);
        }
        return tripRepository.save(trip.get());
    }

    public Trip approveTrip(Long tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            trip.get().setStatus(TripStatusEnum.APPROVED);
        }
        return tripRepository.save(trip.get());
    }

    public void declineTrip(Long tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            trip.get().setStatus(TripStatusEnum.CREATED);
        }
    }

    public List<Trip> getTrips() {
        return tripRepository.findAllByStatusIs(TripStatusEnum.CREATED);
    }

    public List<Trip> getPendingTrips() {
        return tripRepository.findAllByStatusIs(TripStatusEnum.PENDING);
    }

    public List<Trip> getApprovedTrips() {
        return tripRepository.findAllByStatusIs(TripStatusEnum.APPROVED);
    }

    public String getStatus(Long tripId) {
        return tripRepository.getStatus(tripId);
    }
}

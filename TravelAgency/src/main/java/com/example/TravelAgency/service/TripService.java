package com.example.TravelAgency.service;

import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.entity.TripEntity;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.repository.FlightRepository;
import com.example.TravelAgency.repository.TripRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {

    private final TripRepository tripRepository;

    private final MyUserDetailService myUserDetailService;

    @Autowired
    private FlightRepository flightRepository;

    public TripEntity createTrip(TripDto tripDto) {
        UserEntity userEntity = myUserDetailService.getCurrentUser();
        TripEntity tripEntity = TripEntity.builder()
                .tripReason(tripDto.getTripReason())
                .tripDescription(tripDto.getTripDescription())
                .fromPlace(tripDto.getFromPlace())
                .toPlace(tripDto.getToPlace())
                .departureDate(tripDto.getDepartureDate())
                .arrivalDate(tripDto.getArrivalDate())
                .userEntity(userEntity)
                .status("CREATED")
                .build();
        return tripRepository.save(tripEntity);
    }

    public void updateTrip(TripDto tripDto, Long tripId) {
        TripEntity tripEntity = tripRepository.findTripEntityByTripId(tripId);
        tripEntity.setTripReason(tripDto.getTripReason());
        tripEntity.setTripDescription(tripDto.getTripDescription());
        tripEntity.setFromPlace(tripDto.getFromPlace());
        tripEntity.setToPlace(tripDto.getToPlace());
        tripEntity.setDepartureDate(tripDto.getDepartureDate());
        tripEntity.setArrivalDate(tripDto.getArrivalDate());
        tripRepository.save(tripEntity);
    }

    @Transactional
    public void deleteTrip(Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            flightRepository.deleteAllByTripEntity(trip.get());
            tripRepository.deleteItem(tripId);
        }
    }

    public TripEntity sendApproval(Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        if (trip.isPresent()) {
            trip.get().setStatus("PENDING");
        }
        return tripRepository.save(trip.get());
    }

    public TripEntity approveTrip(Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            trip.get().setStatus("APPROVED");
        }
        return tripRepository.save(trip.get());
    }

    public TripEntity declineTrip(Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            trip.get().setStatus("CREATED");
        }
        return tripRepository.save(trip.get());
    }

    public List<TripEntity> getTrips() {
        UserEntity userEntity = myUserDetailService.getCurrentUser();
        return tripRepository.findAllByStatusIsAndUserEntity("CREATED", userEntity);
    }

    public List<TripEntity> getPendingTrips() {
        return tripRepository.findAllByStatusIs("PENDING");
    }

    public List<TripEntity> getApprovedTrips() {
        UserEntity userEntity = myUserDetailService.getCurrentUser();
        return tripRepository.findAllByStatusIsAndUserEntity("APPROVED", userEntity);
    }

    public String getStatus(Long tripId) {
        TripEntity tripEntity = tripRepository.findTripEntityByTripId(tripId);
        return tripEntity.getStatus();
    }
}

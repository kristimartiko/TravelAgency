package com.example.TravelAgency.service;

import com.example.TravelAgency.controller.TripController;
import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.entity.TripEntity;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.repository.FlightRepository;
import com.example.TravelAgency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private static final Logger logger = Logger.getLogger(TripController.class);

    @Autowired
    TripRepository tripRepository;

    @Autowired
    MyUserDetailService myUserDetailService;

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
        logger.debug("TripService: Found trip with id: " + tripEntity.getTripId());
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
            flightRepository.deleteAllByTripEntity(trip.get().getTripId());
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
        return tripRepository.findAllByUserEntity(userEntity);
    }

    public List<TripEntity> getPendingTrips() {
        return tripRepository.findAllByStatusIs("PENDING");
    }

}

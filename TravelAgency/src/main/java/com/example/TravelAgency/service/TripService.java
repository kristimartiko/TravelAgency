package com.example.TravelAgency.service;

import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.entity.TripEntity;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.enumeration.TripStatusEnum;
import com.example.TravelAgency.repository.FlightRepository;
import com.example.TravelAgency.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
                .status(TripStatusEnum.CREATED)
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
            tripRepository.deleteById(tripId);
        }
    }

    public TripEntity sendApproval(Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        if (trip.isPresent()) {
            trip.get().setStatus(TripStatusEnum.PENDING);
        }
        return tripRepository.save(trip.get());
    }

    public TripEntity approveTrip(Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            trip.get().setStatus(TripStatusEnum.APPROVED);
        }
        return tripRepository.save(trip.get());
    }

    public TripEntity declineTrip(Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        if(trip.isPresent()) {
            trip.get().setStatus(TripStatusEnum.CREATED);
        }
        return tripRepository.save(trip.get());
    }

    public List<TripEntity> getTrips() {
        UserEntity userEntity = myUserDetailService.getCurrentUser();
        return tripRepository.findAllByUserEntity(userEntity);
    }

    public List<TripEntity> getPendingTrips() {
        return tripRepository.findAllByStatusIs(TripStatusEnum.PENDING);
    }

    public List<TripEntity> getApprovedTrips() {
        UserEntity userEntity = myUserDetailService.getCurrentUser();
        return tripRepository.findAllByStatusIsAndUserEntity(TripStatusEnum.APPROVED, userEntity);
    }

    public TripStatusEnum getStatus(Long tripId) {
        TripEntity tripEntity = tripRepository.findTripEntityByTripId(tripId);
        return tripEntity.getStatus();
    }
}

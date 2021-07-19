package com.example.TravelAgency.service;

import com.example.TravelAgency.dto.TripDto;
import com.example.TravelAgency.entity.FlightEntity;
import com.example.TravelAgency.entity.TripEntity;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.repository.FlightRepository;
import com.example.TravelAgency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private TripRepository tripRepository;

    public FlightEntity addFlight(Long tripId) {
        TripEntity tripEntity = tripRepository.findTripEntityByTripId(tripId);
        FlightEntity flightEntity = FlightEntity.builder()
                .fromPlace(tripEntity.getFromPlace())
                .toPlace(tripEntity.getToPlace())
                .arrivalDate(tripEntity.getArrivalDate())
                .departureDate(tripEntity.getDepartureDate())
                .tripEntity(tripEntity).build();
        return flightRepository.save(flightEntity);
    }

    public List<FlightEntity> getAllFlights() {
        List<FlightEntity> flights = new ArrayList<FlightEntity>();
        UserEntity userEntity = myUserDetailService.getCurrentUser();
        List<TripEntity> tripEntity = tripRepository.findAllByUserEntity(userEntity);
        for(TripEntity tripEntity1: tripEntity) {
           for(FlightEntity flightElement : flightRepository.findFlightEntityByTripEntity(tripEntity1)) {
               flights.add(flightElement);
           }
        }
        return flights;
    }
}

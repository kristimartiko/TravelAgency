package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.FlightEntity;
import com.example.TravelAgency.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    public List<FlightEntity> findFlightEntityByTripEntity(TripEntity tripEntity);
}

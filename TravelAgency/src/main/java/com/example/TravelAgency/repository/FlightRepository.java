package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.FlightEntity;
import com.example.TravelAgency.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    public List<FlightEntity> findFlightEntityByTripEntity(TripEntity tripEntity);

    @Modifying
    @Query(value = "DELETE FROM flight WHERE trip_id = :trip_id", nativeQuery = true)
    public void deleteAllByTripEntity(@Param("trip_id") Long trip_id);
}

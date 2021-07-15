package com.example.TravelAgency.repository;

import com.example.TravelAgency.enumeration.TripStatusEnum;
import com.example.TravelAgency.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    public void deleteTripById(@Param("trip_id") Long trip_id);

    public List<Trip> findAllByStatusIs(TripStatusEnum tripStatusEnum);

    @Query(value = "SELECT status from Trip  WHERE trip_id :trip_id", nativeQuery = true)
    public String getStatus(@Param("trip_id") Long tripId);
}

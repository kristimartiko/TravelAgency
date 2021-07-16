package com.example.TravelAgency.repository;

import com.example.TravelAgency.enumeration.TripStatusEnum;
import com.example.TravelAgency.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    public void deleteTripEntityByTripId(@Param("trip_id") Long tripId);

    public List<TripEntity> findAllByStatusIs(TripStatusEnum tripStatusEnum);

    @Query(value = "SELECT status from Trip  WHERE trip_id :trip_id", nativeQuery = true)
    public String getStatus(@Param("trip_id") Long tripId);

    public TripEntity findTripEntityByTripId(@Param("trip_id") Long tripId);
}

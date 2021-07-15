package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query(value = "SELECT * FROM trip WHERE status = 'Created'", nativeQuery = true)
    public List<Trip> tripsCreated();

    @Query(value = "SELECT * FROM trip WHERE status_id = 'Waiting For Approval'", nativeQuery = true)
    public List<Trip> tripsSendForApproval();

    @Query(value = "SELECT * FROM trip WHERE status_id = 'Approved'", nativeQuery = true)
    public List<Trip> tripsApproved();

    @Query(value = "SELECT * FROM trip WHERE trip_id = :trip_id", nativeQuery = true)
    public Trip isPresent(@Param("trip_id") Long trip_id);

    @Query(value = "UPDATE trip SET status_id = 'Waiting For Approval' WHERE trip_id = :trip_id", nativeQuery = true)
    public void sendApprove(@Param("trip_id") Long trip_id);

    @Query(value = "UPDATE trip SET sataus_id = 'Approved' WHERE trip_id = :trip_id", nativeQuery = true)
    public void approve(@Param("trip_id") Long trip_id);

    public void deleteTripById(@Param("trip_id") Long trip_id);
}

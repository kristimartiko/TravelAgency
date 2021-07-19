package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.enumeration.TripStatusEnum;
import com.example.TravelAgency.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    //public void deleteTripEntityByTripId(@Param("trip_id") Long tripId);

    public List<TripEntity> findAllByStatusIsAndUserEntity(String status, UserEntity userEntity);

    public List<TripEntity> findAllByStatusIs(String status);

    public TripEntity findTripEntityByTripId(@Param("trip_id") Long tripId);

    public List<TripEntity> findAllByUserEntity(UserEntity userEntity);

}

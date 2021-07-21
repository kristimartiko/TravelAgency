package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    public List<TripEntity> findAllByStatusIs(String status);

    public TripEntity findTripEntityByTripId(Long tripId);

    public List<TripEntity> findAllByUserEntity(UserEntity userEntity);

    @Modifying
    @Query(value = "DELETE FROM trip WHERE trip_id = :trip_id", nativeQuery = true)
    public void deleteItem(@Param("trip_id") Long trip_id);

}

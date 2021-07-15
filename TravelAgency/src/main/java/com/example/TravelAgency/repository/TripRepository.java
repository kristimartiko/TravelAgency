package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {

}

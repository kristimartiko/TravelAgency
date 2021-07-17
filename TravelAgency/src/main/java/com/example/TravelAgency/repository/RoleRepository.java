package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.FlightEntity;
import com.example.TravelAgency.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}

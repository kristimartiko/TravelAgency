package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findUserByEmail(@Param("email") String value);
}

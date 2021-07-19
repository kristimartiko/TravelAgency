package com.example.TravelAgency.repository;

import com.example.TravelAgency.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findUserByEmail(@Param("email") String value);

    public Optional<UserEntity> findUserEntityByEmail(String value);
}

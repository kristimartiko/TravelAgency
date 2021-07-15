package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByEmail(@Param("email") String value);
}

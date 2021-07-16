package com.example.TravelAgency.service;

import com.example.TravelAgency.dto.UserDto;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity addUser(UserDto userDto) {
        UserEntity userEntity = UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword()).build();
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
}

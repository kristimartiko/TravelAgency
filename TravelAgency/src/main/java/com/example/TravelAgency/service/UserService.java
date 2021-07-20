package com.example.TravelAgency.service;

import com.example.TravelAgency.dto.UserDto;
import com.example.TravelAgency.entity.AuthenticationRequest;
import com.example.TravelAgency.entity.AuthenticationResponse;
import com.example.TravelAgency.entity.RoleEntity;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.exception.CustomeException;
import com.example.TravelAgency.repository.RoleRepository;
import com.example.TravelAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public UserEntity addUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Optional<RoleEntity> roleEntity = roleRepository.findById(1L);
        if(roleEntity.isPresent()) {
            userEntity.setRoleEntities(new HashSet<>(Arrays.asList(roleEntity.get())));
        }

        Optional<UserEntity> foundUser = userRepository.findUserEntityByEmail(userDto.getEmail());
        if(foundUser.isPresent()) {
            throw new CustomeException("Oops, user with this email already exists !");
        }

        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

}

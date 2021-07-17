package com.example.TravelAgency.controller;

import com.example.TravelAgency.dto.UserDto;
import com.example.TravelAgency.entity.AuthenticationRequest;
import com.example.TravelAgency.entity.AuthenticationResponse;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.service.MyUserDetailService;
import com.example.TravelAgency.service.UserService;
import com.example.TravelAgency.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final String firstName = myUserDetailService.getCurrentUserName(authenticationRequest.getEmail());

        final UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, firstName));
    }

    @PostMapping(value = "/addUser")
    public UserEntity addUser(@RequestBody UserDto userDto) {
        return this.userService.addUser(userDto);
    }

    @GetMapping(value = "/users")
    public List<UserEntity> getUsers() {
        return this.userService.getUsers();
    }
}

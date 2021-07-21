package com.example.TravelAgency.service;

import com.example.TravelAgency.entity.RoleEntity;
import com.example.TravelAgency.entity.UserEntity;
import com.example.TravelAgency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(s);
        if(userEntity == null) {}

        return new org.springframework.security.core.userdetails.
                User(userEntity.getEmail(), userEntity.getPassword(),mapRolesToAuthorities(userEntity.getRoleEntities()));
    }

    public String getCurrentUserName(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        return userEntity.getFirstName();
    }

    public UserEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserByEmail(auth.getName());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roleEntities) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for(RoleEntity role: roleEntities) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}

package com.telcobright.userresponse.service;


import com.telcobright.userresponse.entity.Role;
import com.telcobright.userresponse.entity.UserInfo;
import com.telcobright.userresponse.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserInfoRepo userRepository;
    @Autowired
    public CustomUserDetailsService(UserInfoRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByEmail(email).get();


        List<Role> roles = user.getRoles();//new ArrayList<>();

        UserDetails userDetails;
        userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(roles))
                .build();
        return userDetails;
    }
}
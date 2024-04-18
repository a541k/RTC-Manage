package com.telcobright.userresponse.repository;

import com.telcobright.userresponse.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByEmail(String email);



}

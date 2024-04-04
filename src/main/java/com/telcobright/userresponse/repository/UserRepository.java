package com.telcobright.userresponse.repository;

import com.telcobright.userresponse.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserInfo, Integer> {
    List<UserInfo> findAllByEmail(String email);
//    List<UserInfo> findAllById();
}

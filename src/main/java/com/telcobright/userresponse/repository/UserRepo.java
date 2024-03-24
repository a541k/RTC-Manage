package com.telcobright.userresponse.repository;

//import com.telcobright.springjwt.model.User;
import com.telcobright.userresponse.dto.LogInData;
import com.telcobright.userresponse.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
//@Component
//@Service
public interface UserRepo extends CrudRepository<UserInfo, Integer> {
    UserInfo findByEmail(String email);
}
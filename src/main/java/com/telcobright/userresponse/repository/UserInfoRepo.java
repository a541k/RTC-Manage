package com.telcobright.userresponse.repository;

import com.telcobright.userresponse.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {


}

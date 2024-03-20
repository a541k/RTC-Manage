package com.telcobright.userresponse.repository;

import com.telcobright.userresponse.entity.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
    //List<Permission> findPermissionByEmail(String email);
}

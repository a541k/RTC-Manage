package com.telcobright.userresponse.repository;

import com.telcobright.userresponse.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Role, Integer> {
    //List<Permission> findPermissionByEmail(String email);
}

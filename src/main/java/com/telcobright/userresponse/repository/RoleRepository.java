package com.telcobright.userresponse.repository;

import com.telcobright.userresponse.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(String name);
}

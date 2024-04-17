package com.telcobright.userresponse.controller;

import com.telcobright.userresponse.entity.Role;
import com.telcobright.userresponse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/auth/")
public class RoleController {
    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    //create permission
    @PostMapping("/createRole")
    ResponseEntity<String> addPermission(@RequestBody Role role){
        return service.addNewRole(role);
    }


    //permit user
    @PostMapping("/setUserRole")
    ResponseEntity<String> givePermissionToUser(@RequestParam("userId") Integer userId, @RequestParam("permissionId") Integer permissionId){
        return service.setRoleToUser(userId, permissionId);
    }
}

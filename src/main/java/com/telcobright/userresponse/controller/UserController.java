package com.telcobright.userresponse.controller;

import com.telcobright.userresponse.dto.LogInData;
import com.telcobright.userresponse.dto.ResponseDto;
import com.telcobright.userresponse.entity.Permission;
import com.telcobright.userresponse.entity.UserInfo;
import com.telcobright.userresponse.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")

public class UserController {

    @Autowired
    ResponseService service;


    @GetMapping("/greet")
    String greetUser(){
        return "hello";
    }


    //log in response
    //@CrossOrigin(origins = "*")
    @PostMapping("/validateUser")
    ResponseEntity<ResponseDto> getUserResponse(@RequestBody LogInData logInData){
        return service.getUserResponse(logInData.getEmail(), logInData.getPassword());
    }


    //create user
    @PostMapping("/user")
    ResponseEntity<String> createUser(@RequestBody UserInfo userData){
        return service.createNewUser(userData);
    }

    //create permission
    @PostMapping("/createPermission")
    ResponseEntity<String> addPermission(@RequestBody Permission permission){
        return service.addNewPermission(permission);
    }


    //permit user
    @PostMapping("/permitUser")
    ResponseEntity<String> givePermissionToUser(@RequestParam("userId") Integer userId, @RequestParam("permissionId") Integer permissionId){
        return service.permitUser(userId, permissionId);
    }
}

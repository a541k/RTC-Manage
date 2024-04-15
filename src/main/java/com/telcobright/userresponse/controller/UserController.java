package com.telcobright.userresponse.controller;

import com.telcobright.userresponse.entity.Role;
import com.telcobright.userresponse.entity.UserInfo;
import com.telcobright.userresponse.repository.UserInfoRepo;
import com.telcobright.userresponse.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/auth/")
@CrossOrigin(origins = "*")

public class UserController {

    @Autowired
    ResponseService service;
    @Autowired
    UserInfoRepo userInfoRepoService;


    @GetMapping("/greet")
    String greetUser(){
        return "greet";
    }


    //create user
    @PostMapping("/addUser")
    ResponseEntity<String> createUser(@RequestBody UserInfo userData){
        return service.createNewUser(userData);
    }

    @DeleteMapping("removeUser/{id}")
    ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id){
        try{
            userInfoRepoService.deleteById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("editUser/{id}")
    ResponseEntity<?> editUser(@PathVariable("id") Integer id, @RequestBody UserInfo user){

        try{
            service.editUser(id, user);
            return new ResponseEntity<>("User edited successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to edit user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allUser")
    List<UserInfo> allUser (){
        return userInfoRepoService.findAll();
    }





    //create permission
    @PostMapping("/createPermission")
    ResponseEntity<String> addPermission(@RequestBody Role permission){
        return service.addNewPermission(permission);
    }


    //permit user
    @PostMapping("/permitUser")
    ResponseEntity<String> givePermissionToUser(@RequestParam("userId") Integer userId, @RequestParam("permissionId") Integer permissionId){
        return service.permitUser(userId, permissionId);
    }
}

package com.telcobright.userresponse.controller;

import com.telcobright.userresponse.entity.Role;
import com.telcobright.userresponse.entity.UserInfo;
import com.telcobright.userresponse.repository.RoleRepository;
import com.telcobright.userresponse.repository.UserInfoRepo;
import com.telcobright.userresponse.service.InMemoryTokenBlacklist;
import com.telcobright.userresponse.service.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/auth/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    private  final ResponseService service;
    private  final UserInfoRepo userInfoRepoService;
    private  final InMemoryTokenBlacklist inMemoryTokenBlacklist;
    private final RoleRepository roleInfoRepo;

//    @Autowired
//    public UserController(ResponseService service, UserInfoRepo userInfoRepoService, InMemoryTokenBlacklist tokenBlacklist) {
//        this.service = service;
//        this.userInfoRepoService = userInfoRepoService;
//        this.inMemoryTokenBlacklist = tokenBlacklist;
//    }


    @GetMapping("/greet")
    String greetUser(){
        return "greet";
    }

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
    List<UserInfo> getAllUser (){
        return userInfoRepoService.findAll();
    }

    @GetMapping("/user/{id}")
    Optional<UserInfo> getUser (@PathVariable Integer id){
        return userInfoRepoService.findById(id);
    }

    @GetMapping("/roles")
    List<Role> getRoles(){
        return (List<Role>) roleInfoRepo.findAll();
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        String token = service.extractTokenFromRequest(request);
        inMemoryTokenBlacklist.addToBlacklist(token);
        return ResponseEntity.ok("Logged out/blacklisted successfully");
    }

}

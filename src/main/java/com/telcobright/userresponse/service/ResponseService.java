package com.telcobright.userresponse.service;

import com.telcobright.userresponse.dto.LoggedInUserData;
import com.telcobright.userresponse.dto.ResponseDto;
import com.telcobright.userresponse.entity.Role;
import com.telcobright.userresponse.entity.UserInfo;
import com.telcobright.userresponse.repository.RoleRepository;
import com.telcobright.userresponse.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    @Autowired
    public ResponseService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    //get user response
    public ResponseEntity<ResponseDto> getUserResponse(String email, String password) {

        List<UserInfo> userList = userRepo.findAllByEmail(email);

        try{
            UserInfo matchedUser = userList.getFirst();

            if(matchedUser.getPassword().equals(password)){
                //password matched
                Optional<UserInfo> optionalResponse = userRepo.findById(matchedUser.getId());

                if(optionalResponse.isPresent()){


                    UserInfo userResponse = optionalResponse.get();

                    LoggedInUserData data = new LoggedInUserData(userResponse.getFirstName()+" "+ userResponse.getLastName(), userResponse.getPhoneNo());
                    //userResponse.setSuccess(true);


                    ResponseDto responseDto = new ResponseDto();
                    responseDto.setData(data);
//                    responseDto.setMessage(userResponse.getMessage());
                    responseDto.setSuccess(true);
                    responseDto.setPermissions(userResponse.getRoles());


                    return new ResponseEntity<>(responseDto, HttpStatus.OK);
                }
            }else{
                throw new Exception("Password did not match");
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(), HttpStatus.NOT_FOUND);
        }

        System.out.println("Failed");

        //user not present with email
        return new ResponseEntity<>(new ResponseDto(), HttpStatus.NOT_FOUND);

    }


    //create user
    public ResponseEntity<String> createNewUser(UserInfo userData) {

        try{
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);
            userData.setCreatedOn(date);

            List<Role> validRoles = new ArrayList<>();


            for(Role role : userData.getRoles()){
                Optional<Role> optionalRole = roleRepo.findByName(role.getName());
                if(optionalRole.isPresent()){
                    validRoles.add(optionalRole.get());
                }else {
                    throw new Exception(role.getName() + " Role invalid");
                }
            }
            userData.setRoles(validRoles);
            userRepo.save(userData);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

//    public List<UserInfo> getAllUser(){
//        return userRepo.findAllById();
//    }

    public void editUser(Integer id, UserInfo user){
        UserInfo existingUser = userRepo.findById(id).orElseThrow(() ->new RuntimeException("User not found"));

        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhoneNo(user.getPhoneNo());
        existingUser.setStatus(user.getStatus());
        existingUser.setPassword(user.getPassword());

        userRepo.save(existingUser);
    }



    public String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}

package com.telcobright.userresponse.service;

import com.telcobright.userresponse.dto.LoggedInUserData;
import com.telcobright.userresponse.dto.ResponseDto;
import com.telcobright.userresponse.entity.Role;
import com.telcobright.userresponse.entity.UserInfo;
import com.telcobright.userresponse.repository.PermissionRepository;
import com.telcobright.userresponse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PermissionRepository permissionRepo;


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


    //create permission
    public ResponseEntity<String> addNewPermission(Role permissionInput) {
        try{
            Role role = new Role();
            role.setRole(permissionInput.getRole());
            permissionRepo.save(role);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);

    }


    //give existing user an existing permission
    public ResponseEntity<String> permitUser(Integer userId, int permissionId) {
        try{
            Optional<UserInfo> optionalUserInfo = userRepo.findById(userId);
            Optional<Role> optionalPermission = permissionRepo.findById(permissionId);
            if(optionalUserInfo.isPresent() && optionalPermission.isPresent()){
                UserInfo user = optionalUserInfo.get();
                Role permission = optionalPermission.get();

                List<Role> userPermissions = user.getRoles();
                userPermissions.add(permission);

                userRepo.save(user);

            }
            else {
                throw new Exception("User/Permission not Found");
            }
            return new ResponseEntity<>("Success", HttpStatus.OK);

        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }


}

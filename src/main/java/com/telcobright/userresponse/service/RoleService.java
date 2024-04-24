package com.telcobright.userresponse.service;

import com.telcobright.userresponse.entity.Role;
import com.telcobright.userresponse.entity.UserInfo;
import com.telcobright.userresponse.repository.RoleRepository;
import com.telcobright.userresponse.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepo;
    private final UserInfoRepo userRepo;

    @Autowired
    public RoleService(RoleRepository permissionRepo,
                       UserInfoRepo userRepo) {
        this.roleRepo = permissionRepo;
        this.userRepo = userRepo;

    }


    //create permission

    public ResponseEntity<String> addNewRole(Role roleInput) {
        try{
            Role role = new Role();
            role.setName(roleInput.getName());
            roleRepo.save(role);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);

    }


    //give existing user an existing permission
    public ResponseEntity<String> setRoleToUser(Integer userId, int permissionId) {
        try{
            Optional<UserInfo> optionalUserInfo = userRepo.findById(userId);
            Optional<Role> optionalPermission = roleRepo.findById(permissionId);
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

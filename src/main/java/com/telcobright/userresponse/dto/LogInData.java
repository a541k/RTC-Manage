package com.telcobright.userresponse.dto;

import com.telcobright.userresponse.entity.UserInfo;
import lombok.Data;
import org.apache.catalina.User;

@Data
public class LogInData {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    public LogInData(String email, String password){
        this.email = email;
        this.password = password;
    }

}

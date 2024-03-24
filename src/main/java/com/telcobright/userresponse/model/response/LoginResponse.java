package com.telcobright.userresponse.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String email;
    private String token;
    private  String fatherName;
}
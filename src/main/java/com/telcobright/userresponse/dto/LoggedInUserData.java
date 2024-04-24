package com.telcobright.userresponse.dto;

import lombok.Data;

@Data
public class LoggedInUserData {
    private String fullName;
    private String phoneNo;

    public LoggedInUserData(String s, String phoneNo) {
        this.fullName = s;
        this.phoneNo = phoneNo;
    }
}

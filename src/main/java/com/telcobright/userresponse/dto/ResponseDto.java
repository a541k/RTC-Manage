package com.telcobright.userresponse.dto;

import com.telcobright.userresponse.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private LoggedInUserData data;
    private String message;
    private boolean isSuccess = false;
    private List<Permission> permissions;
}

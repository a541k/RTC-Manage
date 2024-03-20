package com.telcobright.userresponse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String LastName;

    @Column(nullable = false, unique = true)
    private String phoneNo;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    private String message;

    @ManyToMany
    List<Permission> permissions;
}

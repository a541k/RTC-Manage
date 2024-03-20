package com.telcobright.userresponse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity

public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer Id;

    @Column(unique = true)
    private int permission;
}

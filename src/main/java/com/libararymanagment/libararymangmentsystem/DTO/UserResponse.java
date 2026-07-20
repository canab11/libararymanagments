package com.libararymanagment.libararymangmentsystem.dto;

import com.libararymanagment.libararymangmentsystem.entity.Role;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;

}
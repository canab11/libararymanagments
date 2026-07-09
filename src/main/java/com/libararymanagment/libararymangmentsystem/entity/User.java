package com.libararymanagment.libararymangmentsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

   private Role role;

  }


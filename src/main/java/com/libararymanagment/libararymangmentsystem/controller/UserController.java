package com.libararymanagment.libararymangmentsystem.controller;



import com.libararymanagment.libararymangmentsystem.DTO.UserDTO;

import com.libararymanagment.libararymangmentsystem.entity.User;
import com.libararymanagment.libararymangmentsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // CREATE USER
    @PostMapping
    public User saveUser( @Valid @RequestBody UserDTO dto) {
        return service.saveUser(dto);
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO dto) {
        return service.updateUser(id, dto);
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted successfully";
    }
}




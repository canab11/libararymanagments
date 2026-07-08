package com.libararymanagment.libararymangmentsystem.controller;



import com.libararymanagment.libararymangmentsystem.entity.User;
import com.libararymanagment.libararymangmentsystem.service.UserService;
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

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public User saveUser(
            @RequestBody User user) {
        return service.saveUser(user);
    }
}

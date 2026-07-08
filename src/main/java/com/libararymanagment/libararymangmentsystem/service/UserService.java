package com.libararymanagment.libararymangmentsystem.service;



import com.libararymanagment.libararymangmentsystem.entity.User;
import com.libararymanagment.libararymangmentsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    // GET ALL
    public List<User> getAllUsers() {
        return repository.findAll();
    }


    // GET BY ID
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    // CREATE
    public User saveUser(User user) {
        return repository.save(user);
    }


    // UPDATE
    public User updateUser(Long id, User user) {

        User oldUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());

        return repository.save(oldUser);
    }


    // DELETE
    public String deleteUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        repository.delete(user);

        return "User deleted successfully";
    }
}



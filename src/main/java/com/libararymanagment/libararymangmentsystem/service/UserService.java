package com.libararymanagment.libararymangmentsystem.service;




import com.libararymanagment.libararymangmentsystem.DTO.UserRequest;
import com.libararymanagment.libararymangmentsystem.EXception.UserAlreadyExistsException;
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


    public User saveUser(UserRequest dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return repository.save(user);
    }

    // UPDATE
    public User updateUser(Long id, UserRequest dto) {

        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return repository.save(user);
    }

    // DELETE
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}





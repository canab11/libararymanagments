package com.libararymanagment.libararymangmentsystem.service;



import com.libararymanagment.libararymangmentsystem.entity.Role;
import com.libararymanagment.libararymangmentsystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }
}

package com.libararymanagment.libararymangmentsystem.controller;

import com.libararymanagment.libararymangmentsystem.dto.FineRequest;
import com.libararymanagment.libararymangmentsystem.dto.FineResponse;
import com.libararymanagment.libararymangmentsystem.service.FineService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
public class FineController {

    private final FineService service;

    public FineController(FineService service) {
        this.service = service;
    }

    @GetMapping
    public List<FineResponse> getAllFines() {
        return service.getAllFines();
    }

    @GetMapping("/{id}")
    public FineResponse getFineById(
            @PathVariable Long id) {
        return service.getFineById(id);
    }

    @PostMapping
    public FineResponse saveFine(
            @Valid @RequestBody FineRequest request) {
        return service.saveFine(request);
    }

    @DeleteMapping("/{id}")
    public String deleteFine(
            @PathVariable Long id) {

        service.deleteFine(id);

        return "Fine deleted successfully";
    }
}


package com.libararymanagment.libararymangmentsystem.controller;

import com.libararymanagment.libararymangmentsystem.dto.AuthorRequest;
import com.libararymanagment.libararymangmentsystem.dto.AuthorResponse;
import com.libararymanagment.libararymangmentsystem.service.AuthorService;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin("*")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    // GET ALL AUTHORS
    @GetMapping
    public List<AuthorResponse> getAllAuthors() {

        return service.getAllAuthors();
    }

    // GET AUTHOR BY ID
    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(
            @PathVariable Long id) {

        return service.getAuthorById(id);
    }

    // CREATE AUTHOR
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public AuthorResponse saveAuthor(
            @Valid @RequestBody AuthorRequest request) {

        return service.saveAuthor(request);
    }

    // UPDATE AUTHOR
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public AuthorResponse updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequest request) {

        return service.updateAuthor(id, request);
    }

    // DELETE AUTHOR
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteAuthor(
            @PathVariable Long id) {

        service.deleteAuthor(id);

        return "Author deleted successfully";
    }

}


package com.libararymanagment.libararymangmentsystem.controller;



import com.libararymanagment.libararymangmentsystem.entity.Author;
import com.libararymanagment.libararymangmentsystem.service.AuthorService;

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

    @GetMapping
    public List<Author> getAllAuthors() {
        return service.getAllAuthors();
    }

    @PostMapping
    public Author saveAuthor(
            @RequestBody Author author) {
        return service.saveAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(
            @PathVariable Long id) {
        service.deleteAuthor(id);
    }
}

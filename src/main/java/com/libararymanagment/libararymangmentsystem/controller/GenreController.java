package com.libararymanagment.libararymangmentsystem.controller;

import com.libararymanagment.libararymangmentsystem.dto.GenreRequest;
import com.libararymanagment.libararymangmentsystem.dto.GenreResponse;
import com.libararymanagment.libararymangmentsystem.service.GenreService;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin("*")
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<GenreResponse> getAllGenres() {
        return service.getAllGenres();
    }

    // CREATE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public GenreResponse saveGenre(
            @Valid @RequestBody GenreRequest request) {

        return service.saveGenre(request);
    }

    // DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteGenre(
            @PathVariable Long id) {

        service.deleteGenre(id);

        return "Genre deleted successfully";
    }
}


package com.libararymanagment.libararymangmentsystem.controller;



import com.libararymanagment.libararymangmentsystem.entity.Genre;
import com.libararymanagment.libararymangmentsystem.service.GenreService;

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

    @GetMapping
    public List<Genre> getAllGenres() {
        return service.getAllGenres();
    }

    @PostMapping
    public Genre saveGenre(
            @RequestBody Genre genre) {
        return service.saveGenre(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(
            @PathVariable Long id) {
        service.deleteGenre(id);
    }
}

package com.libararymanagment.libararymangmentsystem.service;



import com.libararymanagment.libararymangmentsystem.entity.Genre;
import com.libararymanagment.libararymangmentsystem.repository.GenreRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<Genre> getAllGenres() {
        return repository.findAll();
    }

    public Genre saveGenre(Genre genre) {
        return repository.save(genre);
    }

    public void deleteGenre(Long id) {
        repository.deleteById(id);
    }
}

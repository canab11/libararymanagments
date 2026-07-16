package com.libararymanagment.libararymangmentsystem.service;


import com.libararymanagment.libararymangmentsystem.DTO.GenreRequest;
import com.libararymanagment.libararymangmentsystem.DTO.GenreResponse;
import com.libararymanagment.libararymangmentsystem.entity.Genre;
import com.libararymanagment.libararymangmentsystem.repository.GenreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }


    // GET ALL
    public List<GenreResponse> getAllGenres() {

        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    // CREATE
    public GenreResponse saveGenre(GenreRequest request) {

        Genre genre = new Genre();

        genre.setGenreName(request.getGenreName());

        Genre savedGenre = repository.save(genre);

        return convertToResponse(savedGenre);
    }


    // DELETE
    public void deleteGenre(Long id) {

        repository.deleteById(id);
    }



    // Entity -> Response
    private GenreResponse convertToResponse(Genre genre) {

        GenreResponse response = new GenreResponse();

        response.setGenreId(genre.getGenreId());
        response.setGenreName(genre.getGenreName());

        return response;
    }
}



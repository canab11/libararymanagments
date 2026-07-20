package com.libararymanagment.libararymangmentsystem.service;

import com.libararymanagment.libararymangmentsystem.dto.AuthorRequest;
import com.libararymanagment.libararymangmentsystem.dto.AuthorResponse;
import com.libararymanagment.libararymangmentsystem.entity.Author;
import com.libararymanagment.libararymangmentsystem.repository.AuthorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    public List<AuthorResponse> getAllAuthors() {

        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public AuthorResponse getAuthorById(Long id) {

        Author author = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Author not found"));

        return convertToResponse(author);
    }

    // CREATE
    public AuthorResponse saveAuthor(AuthorRequest request) {

        Author author = new Author();

        author.setAuthorName(
                request.getAuthorName()
        );

        Author saved = repository.save(author);

        return convertToResponse(saved);
    }

    // UPDATE
    public AuthorResponse updateAuthor(
            Long id,
            AuthorRequest request) {

        Author author = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Author not found"));

        author.setAuthorName(
                request.getAuthorName()
        );

        Author updated = repository.save(author);

        return convertToResponse(updated);
    }

    // DELETE
    public void deleteAuthor(Long id) {

        repository.deleteById(id);
    }

    // ENTITY TO RESPONSE
    private AuthorResponse convertToResponse(Author author) {

        AuthorResponse response = new AuthorResponse();

        response.setAuthorId(
                author.getAuthorId()
        );

        response.setAuthorName(
                author.getAuthorName()
        );

        return response;
    }

}

package com.libararymanagment.libararymangmentsystem.service;



import com.libararymanagment.libararymangmentsystem.entity.Author;
import com.libararymanagment.libararymangmentsystem.repository.AuthorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Author saveAuthor(Author author) {
        return repository.save(author);
    }

    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }
}

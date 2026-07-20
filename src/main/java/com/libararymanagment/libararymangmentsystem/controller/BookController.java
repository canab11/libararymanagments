package com.libararymanagment.libararymangmentsystem.controller;

import com.libararymanagment.libararymangmentsystem.dto.BookRequest;
import com.libararymanagment.libararymangmentsystem.dto.BookResponse;
import com.libararymanagment.libararymangmentsystem.service.BookService;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // GET ALL BOOKS
    @GetMapping
    public List<BookResponse> getAllBooks() {

        return service.getAllBooks();
    }

    // GET BOOK BY ID
    @GetMapping("/{id}")
    public BookResponse getBookById(
            @PathVariable Long id) {

        return service.getBookById(id);
    }

    // CREATE BOOK
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public BookResponse saveBook(
            @Valid @RequestBody BookRequest request) {

        return service.saveBook(request);
    }

    // UPDATE BOOK
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BookResponse updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {

        return service.updateBook(id, request);
    }

    // DELETE BOOK
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteBook(
            @PathVariable Long id) {

        service.deleteBook(id);

        return "Book deleted successfully";
    }

    // SEARCH BY TITLE
    @GetMapping("/search/title")
    public List<BookResponse> searchByTitle(
            @RequestParam String title) {

        return service.searchByTitle(title);
    }

    // SEARCH BY AUTHOR
    @GetMapping("/search/author")
    public List<BookResponse> searchByAuthor(
            @RequestParam String author) {

        return service.searchByAuthor(author);
    }

    // SEARCH BY GENRE
    @GetMapping("/search/genre")
    public List<BookResponse> searchByGenre(
            @RequestParam String genre) {

        return service.searchByGenre(genre);
    }

}


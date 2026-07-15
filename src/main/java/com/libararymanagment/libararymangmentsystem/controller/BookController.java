package com.libararymanagment.libararymangmentsystem.controller;



import com.libararymanagment.libararymangmentsystem.entity.Book;
import com.libararymanagment.libararymangmentsystem.service.BookService;

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

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return service.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,
                           @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }

    @GetMapping("/search/title")
    public List<Book> searchByTitle(
            @RequestParam String title) {
        return service.searchByTitle(title);
    }

    @GetMapping("/search/author")
    public List<Book> searchByAuthor(
            @RequestParam String author) {
        return service.searchByAuthor(author);
    }

    @GetMapping("/search/genre")
    public List<Book> searchByGenre(
            @RequestParam String genre) {
        return service.searchByGenre(genre);
    }
}

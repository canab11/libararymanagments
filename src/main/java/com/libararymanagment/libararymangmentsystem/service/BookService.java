package com.libararymanagment.libararymangmentsystem.service;



import com.libararymanagment.libararymangmentsystem.entity.Book;
import com.libararymanagment.libararymangmentsystem.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setTitle(book.getTitle());
            existing.setIsbn(book.getIsbn());
            existing.setPublicationYear(book.getPublicationYear());
            existing.setAvailableCopies(book.getAvailableCopies());
            existing.setAuthor(book.getAuthor());
            existing.setGenre(book.getGenre());

            return repository.save(existing);
        }

        return null;
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    public List<Book> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByAuthor(String author) {
        return repository.findByAuthorAuthorNameContainingIgnoreCase(author);
    }

    public List<Book> searchByGenre(String genre) {
        return repository.findByGenreGenreNameContainingIgnoreCase(genre);
    }
}

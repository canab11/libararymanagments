package com.libararymanagment.libararymangmentsystem.service;




import com.libararymanagment.libararymangmentsystem.DTO.BookRequest;
import com.libararymanagment.libararymangmentsystem.DTO.BookResponse;
import com.libararymanagment.libararymangmentsystem.entity.Author;
import com.libararymanagment.libararymangmentsystem.entity.Book;
import com.libararymanagment.libararymangmentsystem.entity.Genre;
import com.libararymanagment.libararymangmentsystem.repository.AuthorRepository;
import com.libararymanagment.libararymangmentsystem.repository.BookRepository;
import com.libararymanagment.libararymangmentsystem.repository.GenreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {


    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;


    public BookService(
            BookRepository repository,
            AuthorRepository authorRepository,
            GenreRepository genreRepository) {

        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }




    // GET ALL BOOKS
    public List<BookResponse> getAllBooks() {

        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }





    // GET BOOK BY ID
    public BookResponse getBookById(Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        return convertToResponse(book);
    }





    // CREATE BOOK
    public BookResponse saveBook(BookRequest request) {


        Book book = new Book();


        book.setTitle(request.getTitle());

        book.setIsbn(request.getIsbn());

        book.setPublicationYear(
                request.getPublicationYear()
        );

        book.setAvailableCopies(
                request.getAvailableCopies()
        );



        Author author = authorRepository
                .findById(request.getAuthorId())
                .orElseThrow(() ->
                        new RuntimeException("Author not found"));



        Genre genre = genreRepository
                .findById(request.getGenreId())
                .orElseThrow(() ->
                        new RuntimeException("Genre not found"));



        book.setAuthor(author);

        book.setGenre(genre);



        Book savedBook = repository.save(book);


        return convertToResponse(savedBook);
    }





    // UPDATE BOOK
    public BookResponse updateBook(
            Long id,
            BookRequest request) {


        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));



        book.setTitle(request.getTitle());

        book.setIsbn(request.getIsbn());

        book.setPublicationYear(
                request.getPublicationYear()
        );

        book.setAvailableCopies(
                request.getAvailableCopies()
        );



        Author author = authorRepository
                .findById(request.getAuthorId())
                .orElseThrow(() ->
                        new RuntimeException("Author not found"));



        Genre genre = genreRepository
                .findById(request.getGenreId())
                .orElseThrow(() ->
                        new RuntimeException("Genre not found"));



        book.setAuthor(author);

        book.setGenre(genre);



        Book updatedBook = repository.save(book);



        return convertToResponse(updatedBook);
    }





    // DELETE BOOK
    public void deleteBook(Long id) {

        repository.deleteById(id);
    }





    // SEARCH BY TITLE
    public List<BookResponse> searchByTitle(String title) {

        return repository
                .findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }





    // SEARCH BY AUTHOR
    public List<BookResponse> searchByAuthor(String author) {

        return repository
                .findByAuthorAuthorNameContainingIgnoreCase(author)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }





    // SEARCH BY GENRE
    public List<BookResponse> searchByGenre(String genre) {

        return repository
                .findByGenreGenreNameContainingIgnoreCase(genre)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }





    // CONVERT BOOK ENTITY TO BOOK RESPONSE
    private BookResponse convertToResponse(Book book) {


        BookResponse response = new BookResponse();


        response.setBookId(
                book.getBookId()
        );


        response.setTitle(
                book.getTitle()
        );


        response.setIsbn(
                book.getIsbn()
        );


        response.setPublicationYear(
                book.getPublicationYear()
        );


        response.setAvailableCopies(
                book.getAvailableCopies()
        );



        if(book.getAuthor() != null){

            response.setAuthorId(
                    book.getAuthor().getAuthorId()
            );
        }



        if(book.getGenre() != null){

            response.setGenreId(
                    book.getGenre().getGenreId()
            );
        }



        return response;
    }

}













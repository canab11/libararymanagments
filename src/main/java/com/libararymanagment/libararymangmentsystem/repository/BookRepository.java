package com.libararymanagment.libararymangmentsystem.repository;



import com.libararymanagment.libararymangmentsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorAuthorNameContainingIgnoreCase(String author);

    List<Book> findByGenreGenreNameContainingIgnoreCase(String genre);

}





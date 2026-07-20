package com.libararymanagment.libararymangmentsystem.dto;

import lombok.Data;

@Data
public class BookResponse {

    private Long bookId;

    private String title;

    private String isbn;

    private Integer publicationYear;

    private Integer availableCopies;

    private Long authorId;

    private Long genreId;
}

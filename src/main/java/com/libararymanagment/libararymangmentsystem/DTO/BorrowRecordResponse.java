package com.libararymanagment.libararymangmentsystem.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRecordResponse {

    private Long borrowId;

    private Long memberId;

    private Long bookId;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private String status;
}


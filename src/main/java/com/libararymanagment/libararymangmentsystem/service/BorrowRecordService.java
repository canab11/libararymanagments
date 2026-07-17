package com.libararymanagment.libararymangmentsystem.service;



import com.libararymanagment.libararymangmentsystem.DTO.BorrowRecordRequest;
import com.libararymanagment.libararymangmentsystem.DTO.BorrowRecordResponse;
import com.libararymanagment.libararymangmentsystem.entity.Book;
import com.libararymanagment.libararymangmentsystem.entity.BorrowRecord;
import com.libararymanagment.libararymangmentsystem.entity.Member;
import com.libararymanagment.libararymangmentsystem.repository.BookRepository;
import com.libararymanagment.libararymangmentsystem.repository.BorrowRecordRepository;
import com.libararymanagment.libararymangmentsystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository repository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public BorrowRecordService(
            BorrowRecordRepository repository,
            MemberRepository memberRepository,
            BookRepository bookRepository) {

        this.repository = repository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    // GET ALL
    public List<BorrowRecordResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public BorrowRecordResponse getById(Long id) {

        BorrowRecord record = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Borrow Record not found"));

        return convertToResponse(record);
    }

    // CREATE
    public BorrowRecordResponse save(
            BorrowRecordRequest request) {

        Member member = memberRepository
                .findById(request.getMemberId())
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));

        Book book = bookRepository
                .findById(request.getBookId())
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        BorrowRecord record = new BorrowRecord();

        record.setMember(member);
        record.setBook(book);
        record.setBorrowDate(request.getBorrowDate());
        record.setDueDate(request.getDueDate());
        record.setReturnDate(request.getReturnDate());
        record.setStatus(request.getStatus());

        BorrowRecord savedRecord =
                repository.save(record);

        return convertToResponse(savedRecord);
    }

    // DELETE
    public void delete(Long id) {

        repository.deleteById(id);
    }

    // CONVERT ENTITY TO RESPONSE
    private BorrowRecordResponse convertToResponse(
            BorrowRecord record) {

        BorrowRecordResponse response =
                new BorrowRecordResponse();

        response.setBorrowId(
                record.getBorrowId());

        response.setMemberId(
                record.getMember().getMemberId());

        response.setBookId(
                record.getBook().getBookId());

        response.setBorrowDate(
                record.getBorrowDate());

        response.setDueDate(
                record.getDueDate());

        response.setReturnDate(
                record.getReturnDate());

        response.setStatus(
                record.getStatus());

        return response;
    }
    // RETURN BOOK
    public BorrowRecordResponse returnBook(Long id) {

        BorrowRecord record = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Borrow Record not found"));


        record.setReturnDate(LocalDate.now());

        record.setStatus("RETURNED");


        BorrowRecord updated = repository.save(record);


        return convertToResponse(updated);
    }
}

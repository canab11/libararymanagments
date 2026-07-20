package com.libararymanagment.libararymangmentsystem.service;

import com.libararymanagment.libararymangmentsystem.dto.BorrowRecordRequest;
import com.libararymanagment.libararymangmentsystem.dto.BorrowRecordResponse;
import com.libararymanagment.libararymangmentsystem.entity.Book;
import com.libararymanagment.libararymangmentsystem.entity.BorrowRecord;
import com.libararymanagment.libararymangmentsystem.entity.Fine;
import com.libararymanagment.libararymangmentsystem.entity.Member;
import com.libararymanagment.libararymangmentsystem.repository.BookRepository;
import com.libararymanagment.libararymangmentsystem.repository.BorrowRecordRepository;
import com.libararymanagment.libararymangmentsystem.repository.FineRepository;
import com.libararymanagment.libararymangmentsystem.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {

    // Fine rate: $1.0 per day late
    private static final double FINE_RATE_PER_DAY = 1.0;

    private final BorrowRecordRepository repository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final FineRepository fineRepository;

    public BorrowRecordService(
            BorrowRecordRepository repository,
            MemberRepository memberRepository,
            BookRepository bookRepository,
            FineRepository fineRepository) {

        this.repository = repository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.fineRepository = fineRepository;
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
                .orElseThrow(() -> new RuntimeException("Borrow Record not found with id: " + id));
        return convertToResponse(record);
    }

    // GET ALL OVERDUE RECORDS
    public List<BorrowRecordResponse> getOverdueRecords() {
        return repository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now())
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // BORROW BOOK - with availability check and inventory decrement
    @Transactional
    public BorrowRecordResponse save(BorrowRecordRequest request) {

        Member member = memberRepository
                .findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + request.getMemberId()));

        Book book = bookRepository
                .findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + request.getBookId()));

        // Check if book has available copies
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book '" + book.getTitle() + "' has no available copies to borrow.");
        }

        // Decrement available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord();
        record.setMember(member);
        record.setBook(book);
        record.setBorrowDate(request.getBorrowDate());
        record.setDueDate(request.getDueDate());
        record.setReturnDate(null);
        record.setStatus("BORROWED");

        BorrowRecord savedRecord = repository.save(record);
        return convertToResponse(savedRecord);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // RETURN BOOK - with inventory increment and automatic fine calculation
    @Transactional
    public BorrowRecordResponse returnBook(Long id) {

        BorrowRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow Record not found with id: " + id));

        if ("RETURNED".equals(record.getStatus())) {
            throw new RuntimeException("This book has already been returned.");
        }

        LocalDate returnDate = LocalDate.now();
        record.setReturnDate(returnDate);
        record.setStatus("RETURNED");

        // Increment available copies back
        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        // Automatic fine calculation if returned late
        LocalDate dueDate = record.getDueDate();
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            double fineAmount = daysLate * FINE_RATE_PER_DAY;

            Fine fine = new Fine();
            fine.setBorrowRecord(record);
            fine.setDaysLate((int) daysLate);
            fine.setFineAmount(fineAmount);
            fine.setPaymentStatus("UNPAID");
            fineRepository.save(fine);
        }

        BorrowRecord updated = repository.save(record);
        return convertToResponse(updated);
    }

    // CONVERT ENTITY TO RESPONSE
    private BorrowRecordResponse convertToResponse(BorrowRecord record) {
        BorrowRecordResponse response = new BorrowRecordResponse();
        response.setBorrowId(record.getBorrowId());
        response.setMemberId(record.getMember().getMemberId());
        response.setBookId(record.getBook().getBookId());
        response.setBorrowDate(record.getBorrowDate());
        response.setDueDate(record.getDueDate());
        response.setReturnDate(record.getReturnDate());
        response.setStatus(record.getStatus());
        return response;
    }
}

package com.libararymanagment.libararymangmentsystem.controller;

import com.libararymanagment.libararymangmentsystem.dto.BorrowRecordRequest;
import com.libararymanagment.libararymangmentsystem.dto.BorrowRecordResponse;
import com.libararymanagment.libararymangmentsystem.service.BorrowRecordService;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow-records")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
public class BorrowRecordController {

    private final BorrowRecordService service;

    public BorrowRecordController(
            BorrowRecordService service) {
        this.service = service;
    }

    @GetMapping
    public List<BorrowRecordResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BorrowRecordResponse getById(
            @PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public BorrowRecordResponse save(
            @Valid @RequestBody BorrowRecordRequest request) {
        return service.save(request);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        service.delete(id);

        return "Borrow Record deleted successfully";
    }
    @GetMapping("/overdue")
    public List<BorrowRecordResponse> getOverdue() {
        return service.getOverdueRecords();
    }

    @PutMapping("/{id}/return")
    public BorrowRecordResponse returnBook(
            @PathVariable Long id) {

        return service.returnBook(id);
    }
}

package com.libararymanagment.libararymangmentsystem.service;

import com.libararymanagment.libararymangmentsystem.DTO.FineRequest;
import com.libararymanagment.libararymangmentsystem.DTO.FineResponse;
import com.libararymanagment.libararymangmentsystem.entity.BorrowRecord;
import com.libararymanagment.libararymangmentsystem.entity.Fine;
import com.libararymanagment.libararymangmentsystem.repository.BorrowRecordRepository;
import com.libararymanagment.libararymangmentsystem.repository.FineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineService {

    private final FineRepository repository;
    private final BorrowRecordRepository borrowRecordRepository;

    public FineService(
            FineRepository repository,
            BorrowRecordRepository borrowRecordRepository) {

        this.repository = repository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    // GET ALL
    public List<FineResponse> getAllFines() {

        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public FineResponse getFineById(Long id) {

        Fine fine = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fine not found"));

        return convertToResponse(fine);
    }

    // CREATE
    public FineResponse saveFine(FineRequest request) {

        BorrowRecord borrowRecord =
                borrowRecordRepository
                        .findById(request.getBorrowId())
                        .orElseThrow(() ->
                                new RuntimeException("Borrow Record not found"));

        Fine fine = new Fine();

        fine.setBorrowRecord(borrowRecord);
        fine.setDaysLate(request.getDaysLate());
        fine.setFineAmount(request.getFineAmount());
        fine.setPaymentStatus(request.getPaymentStatus());

        Fine savedFine = repository.save(fine);

        return convertToResponse(savedFine);
    }

    // DELETE
    public void deleteFine(Long id) {

        repository.deleteById(id);
    }

    // CONVERT ENTITY TO RESPONSE
    private FineResponse convertToResponse(Fine fine) {

        FineResponse response = new FineResponse();

        response.setFineId(
                fine.getFineId()
        );

        response.setBorrowId(
                fine.getBorrowRecord().getBorrowId()
        );

        response.setDaysLate(
                fine.getDaysLate()
        );

        response.setFineAmount(
                fine.getFineAmount()
        );

        response.setPaymentStatus(
                fine.getPaymentStatus()
        );

        return response;
    }
}

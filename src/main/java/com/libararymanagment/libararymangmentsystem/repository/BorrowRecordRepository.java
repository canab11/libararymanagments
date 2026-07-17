package com.libararymanagment.libararymangmentsystem.repository;


import com.libararymanagment.libararymangmentsystem.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository
        extends JpaRepository<BorrowRecord, Long> {
}


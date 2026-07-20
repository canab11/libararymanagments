package com.libararymanagment.libararymangmentsystem.repository;

import com.libararymanagment.libararymangmentsystem.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    // Overdue: due date is past today and not yet returned
    List<BorrowRecord> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);

    // Count active borrows (not yet returned)
    long countByReturnDateIsNull();

    // Count overdue borrows
    long countByDueDateBeforeAndReturnDateIsNull(LocalDate date);
}


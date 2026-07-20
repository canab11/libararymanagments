package com.libararymanagment.libararymangmentsystem.repository;

import com.libararymanagment.libararymangmentsystem.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FineRepository extends JpaRepository<Fine, Long> {

    // Sum of all unpaid fines
    @Query("SELECT COALESCE(SUM(f.fineAmount), 0.0) FROM Fine f WHERE f.paymentStatus = 'UNPAID'")
    Double sumUnpaidFines();
}

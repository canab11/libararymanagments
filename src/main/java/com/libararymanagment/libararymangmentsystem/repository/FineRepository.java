package com.libararymanagment.libararymangmentsystem.repository;



import com.libararymanagment.libararymangmentsystem.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository
        extends JpaRepository<Fine, Long> {
}

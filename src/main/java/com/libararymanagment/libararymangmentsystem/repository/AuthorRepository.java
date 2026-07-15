package com.libararymanagment.libararymangmentsystem.repository;

import com.libararymanagment.libararymangmentsystem.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
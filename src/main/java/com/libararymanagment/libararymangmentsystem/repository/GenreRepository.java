package com.libararymanagment.libararymangmentsystem.repository;

import com.libararymanagment.libararymangmentsystem.entity.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

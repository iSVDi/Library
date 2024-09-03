package com.example.repository;

import com.example.model.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<LibraryBook, UUID> {
}

package com.example.controller;

import com.example.model.LibraryBook;
import com.example.service.LibraryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@AllArgsConstructor
@Validated
public class LibraryController {
    private final LibraryService service;

    // create
    @PostMapping("createBook")
    void createBook(@Valid @RequestBody LibraryBook libraryBook) {
        service.createBook(libraryBook);
    }

    // read
    @GetMapping("readBook")
    LibraryBook readBook(@RequestParam(value = "uuid") UUID id) {
        return service.readBook(id);
    }

    @GetMapping("readAll")
    List<LibraryBook> readAllBook(Pageable pageable) {
        return service.readAllBooks(pageable);
    }

    // update
    @PutMapping("updateBook")
    void updateBook(@Valid @RequestBody LibraryBook libraryBook) {
        service.updateBook(libraryBook);
    }

    // delete
    @DeleteMapping("deleteBook")
    void deleteBook(@RequestParam(value = "uuid") UUID id) {
        service.deleteBook(id);
    }

}

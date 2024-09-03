package com.example.service;

import com.example.exception.LibraryException;
import com.example.model.Author;
import com.example.model.LibraryBook;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void createBook(LibraryBook book) {

        if (book.getAuthor() != null) {
            Author author = book.getAuthor();
            author.setBooks(List.of(book));
            authorRepository.save(author);
        }
        LibraryBook savedBook = bookRepository.save(book);
    }

    public LibraryBook readBook(UUID id) {
        Optional<LibraryBook> optionalLibraryBook = bookRepository.findById(id);
        if (optionalLibraryBook.isPresent()) {
            return optionalLibraryBook.get();
        }
        throw new LibraryException("Book with such id doesn't exist");
    }

    public List<LibraryBook> readAllBooks(Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        Page<LibraryBook> bookPage = bookRepository.findAll(pageRequest);
        return bookPage.getContent();
    }

    public void updateBook(LibraryBook book) {
        if (bookRepository.existsById(book.getId())) {
            bookRepository.save(book);
        }
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}

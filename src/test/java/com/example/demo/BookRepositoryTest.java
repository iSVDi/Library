package com.example.demo;

import com.example.model.LibraryBook;
import com.example.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    LibraryBook createSavedBook() {
        LibraryBook book = LibraryBook.builder()
                .title("The Great Adventure")
                .genre("Adventure")
                .publicationDate(LocalDate.of(2023, 9, 10))
                .isbn("978-3-161-48410-0")
                .summary("A thrilling journey across unknown lands, where the protagonist faces challenges that test their courage and resolve.")
                .publisher("Epic Publishing House")
                .pageCount(350)
                .language("English")
                .build();
        bookRepository.save(book);
        return book;
    }

    //create
    @Test
    void createBook() {
        LibraryBook book = createSavedBook();
        Assertions.assertTrue(bookRepository.existsById(book.getId()));
    }

    //read
    @Test
    void readBook() {
        LibraryBook book = createSavedBook();
        Assertions.assertTrue(bookRepository.findById(book.getId()).isPresent());
    }

    //update
    @Test
    void updateBook() {
        LibraryBook firstBook = createSavedBook();

        firstBook.setTitle("TestTitle");

        LibraryBook savedFirstBook = bookRepository.save(firstBook);

        Assertions.assertTrue(bookRepository.existsById(savedFirstBook.getId()));
        Assertions.assertEquals("TestTitle", savedFirstBook.getTitle());

    }

    @Test
    void deleteBook() {
        LibraryBook book = bookRepository.save(createSavedBook());
        bookRepository.deleteById(book.getId());
        Assertions.assertTrue(bookRepository.findById(book.getId()).isEmpty());
    }

    //delete

}

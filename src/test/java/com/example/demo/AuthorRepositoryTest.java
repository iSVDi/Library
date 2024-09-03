package com.example.demo;

import com.example.model.Author;
import com.example.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    Author createSavedAuthor() {
        Author author = Author.builder()
                .firstName("John")
                .lastName("Doe")
                .nationality("American")
                .biography("John Doe is a prolific writer known for his contributions to modern literature. His works explore themes of identity, culture, and the human condition.")
                .dateOfBirth(LocalDate.of(1980, 4, 15)).build();
        authorRepository.save(author);
        return author;
    }

    @Test
    void createUser() {
        Author author = createSavedAuthor();
        Assertions.assertTrue(authorRepository.existsById(author.getId()));
    }

    @Test
    void readUser() {
        Author author = createSavedAuthor();
        Assertions.assertTrue(authorRepository.findById(author.getId()).isPresent());
    }

    @Test
    void updateUser() {
        Author author = createSavedAuthor();
        author.setFirstName("TestFirstName");
        Author savedAuthor = authorRepository.save(author);
        Assertions.assertEquals(savedAuthor.getFirstName(), "TestFirstName");
    }

    @Test
    void deleteAuthor() {
        Author author = createSavedAuthor();
        authorRepository.delete(author);
        Assertions.assertTrue(authorRepository.findById(author.getId()).isEmpty());
    }



}

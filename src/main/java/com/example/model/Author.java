package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/*
1. Author Entity
The Author entity represents a person who writes books.

Properties:
- AuthorID: (Primary Key) A unique identifier for each author.
Type: Integer (or UUID)
- FirstName: The first name of the author.
Type: String/VARCHAR
- LastName: The last name of the author.
Type: String/VARCHAR
- DateOfBirth: The author's date of birth.
Type: Date
- Nationality: The nationality of the author.
Type: String/VARCHAR
- Biography: A short biography of the author.
Type: Text
- Website: The author’s official website.
Type: String/VARCHAR
- CreatedAt: Timestamp when the author record was created.
Type: DateTime
- UpdatedAt: Timestamp when the author record was last updated.
Type: DateTime
*/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String nationality;

    @NotBlank
    private String biography;

    @DateTimeFormat(pattern = "dd.mm.yyyy")
    @Past
    @NotNull
    @NotEmpty
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @Valid
    private List<Book> books;
}

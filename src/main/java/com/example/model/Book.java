package com.example.model;

/*

2. Book Entity
The Book entity represents a literary work written by an author.

Properties:
- BookID: (Primary Key) A unique identifier for each book.
Type: Integer (or UUID)
- Title: The title of the book.
Type: String/VARCHAR
- Genre: The genre of the book (e.g., Fiction, Non-Fiction, Sci-Fi, etc.).
Type: String/VARCHAR
- PublicationDate: The date the book was published.
Type: Date
- ISBN: The International Standard Book Number of the book.
Type: String/VARCHAR
- Summary: A brief summary or description of the book.
Type: Text
- AuthorID: (Foreign Key) References the Author entity.
Type: Integer
- Publisher: The publisher of the book.
Type: String/VARCHAR
- PageCount: The number of pages in the book.
Type: Integer
- Language: The language in which the book is written.
Type: String/VARCHAR
- CreatedAt: Timestamp when the book record was created.
Type: DateTime
- UpdatedAt: Timestamp when the book record was last updated.
Type: DateTime

 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private LocalDate publicationDate;

    @NotBlank
    private String isbn;

    @NotBlank
    private String summary;

    @NotBlank
    private String publisher;

    @Min(10)
    private Integer pageCount;

    @NotBlank
    private String language;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}

package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Valid
    @NotNull
    private LocalDate publicationDate;

    @NotBlank
    @Pattern(regexp = "^[0-9]{3}-[0-9]{1}-[0-9]{3}-[0-9]{5}-[0-9]{1}")
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
    @Valid
    private Author author;
}

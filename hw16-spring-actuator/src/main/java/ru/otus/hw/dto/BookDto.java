package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;
}

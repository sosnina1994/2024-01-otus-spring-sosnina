package ru.otus.hw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;
}

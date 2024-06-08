package ru.otus.hw.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class BookCreateDto {
    @NotBlank(message = "Book title can't be null")
    @Size(min = 1, max = 100, message = "Book title should be with size from 1 to 100 symbols")
    private String title;

    @NotNull(message = "Author id is can't be null")
    private Long authorId;

    @NotNull(message = "Genre id is can't be null")
    private Long genreId;
}

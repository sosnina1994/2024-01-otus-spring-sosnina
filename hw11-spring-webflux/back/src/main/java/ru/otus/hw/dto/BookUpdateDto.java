package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BookUpdateDto {
    @NotBlank(message = "Book id is can't be null")
    private String id;

    private String title;

    private String authorId;

    private String genreId;
}

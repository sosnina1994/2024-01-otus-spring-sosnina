package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentCreateDto {
    @NotBlank(message = "Book title can't be null")
    @Size(min = 1, max = 100, message = "Comment text should be with " +
            "size from 1 to 100 symbols")
    private String text;

    @NotBlank(message = "Book id can't be null")
    private String bookId;
}

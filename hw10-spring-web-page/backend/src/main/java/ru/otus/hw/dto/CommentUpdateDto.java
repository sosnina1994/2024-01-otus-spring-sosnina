package ru.otus.hw.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CommentUpdateDto {
    @NotNull(message = "Comment id can't be null")
    private Long id;

    private String text;

    private Long bookId;
}

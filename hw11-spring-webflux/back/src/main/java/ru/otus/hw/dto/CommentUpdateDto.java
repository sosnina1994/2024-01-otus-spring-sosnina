package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CommentUpdateDto {
    @NotBlank(message = "Comment id can't be null")
    private String id;

    private String text;

    private String bookId;
}

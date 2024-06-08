package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;

    private String text;

    private Long bookId;
}

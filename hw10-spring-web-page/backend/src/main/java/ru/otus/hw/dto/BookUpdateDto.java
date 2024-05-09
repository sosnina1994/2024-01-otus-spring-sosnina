package ru.otus.hw.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BookUpdateDto {
    @NotNull(message = "Book id is can't be null")
    private Long id;

    private String title;

    private Long authorId;

    private Long genreId;

    public static BookUpdateDto fromBookDto(BookDto bookDto) {
        return new BookUpdateDto(bookDto.getId(), bookDto.getTitle(),
                bookDto.getAuthor() == null ? null : bookDto.getAuthor().getId(),
                bookDto.getGenre() == null ? null : bookDto.getGenre().getId());
    }
}

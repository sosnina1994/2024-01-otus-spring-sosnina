package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BookUpdateDto {
    @NotNull
    private Long id;

    @NotBlank(message = "Book title can't be null")
    @Size(min = 1, max = 100, message = "Book title should be with size from 1 to 100 symbols")
    private String title;


    @NotNull(message = "Author id is can't be null")
    private Long authorId;

    @NotNull(message = "Genre id is can't be null")
    private Long genreId;

    public static BookUpdateDto fromBookDto(BookDto bookDto) {
        return new BookUpdateDto(bookDto.getId(), bookDto.getTitle(),
                bookDto.getAuthor() == null ? null : bookDto.getAuthor().getId(),
                bookDto.getGenre() == null ? null : bookDto.getGenre().getId());
    }
}

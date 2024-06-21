package ru.otus.hw.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.dto.BookCreateDto;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.dto.BookUpdateDto;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

@Component
@AllArgsConstructor
public class BookMapper {

    private final AuthorMapper authorMapper;

    private final GenreMapper genreMapper;

    public Book toModel(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(),
                authorMapper.toModel(dto.getAuthor()),
                genreMapper.toModel(dto.getGenre()));
    }

    public Book toModel(BookCreateDto dto, Author author, Genre genre) {
        return new Book(null, dto.getTitle(), author, genre);
    }

    public Book toModel(BookUpdateDto dto, Author author, Genre genre) {
        return new Book(dto.getId(), dto.getTitle(),
                author, genre);
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(),
                authorMapper.toDto(book.getAuthor()),
                genreMapper.toDto(book.getGenre()));
    }
}

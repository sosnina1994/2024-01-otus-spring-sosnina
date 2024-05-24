package ru.otus.hw.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.hw.dto.BookCreateDto;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.dto.BookUpdateDto;

import java.util.List;

public interface BookService {
    @PreAuthorize("hasRole('ADMIN')")
    BookDto findById(Long id);

    @PreAuthorize("hasRole('USER')")
    List<BookDto> findAll();

    @PreAuthorize("hasRole('ADMIN')")
    BookDto create(BookCreateDto bookDto);

    @PreAuthorize("hasRole('ADMIN')")
    BookDto update(BookUpdateDto bookDto);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(Long id);
}

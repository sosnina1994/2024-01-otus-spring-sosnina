package ru.otus.hw.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.hw.dto.CommentDto;

import java.util.List;

public interface CommentService {
    @PreAuthorize("hasRole('USER')")
    CommentDto findById(Long id);

    @PreAuthorize("hasRole('USER')")
    List<CommentDto> findAllForBook(Long bookId);

    @PreAuthorize("hasRole('USER')")
    CommentDto create(String text, Long bookId);

    @PreAuthorize("hasRole('USER')")
    CommentDto update(Long id, String text);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(Long id);
}

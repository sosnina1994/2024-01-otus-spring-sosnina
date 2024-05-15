package ru.otus.hw.services;

import ru.otus.hw.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto findById(Long id);

    List<CommentDto> findAllForBook(Long bookId);

    CommentDto create(String text, Long bookId);

    CommentDto update(Long id, String text);

    void deleteById(Long id);
}

package ru.otus.hw.services;

import ru.otus.hw.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(String id);

    List<Comment> findAllForBook(String bookId);

    Comment create(String text, String bookId);

    Comment update(String id, String text);

    void deleteById(String id);
}

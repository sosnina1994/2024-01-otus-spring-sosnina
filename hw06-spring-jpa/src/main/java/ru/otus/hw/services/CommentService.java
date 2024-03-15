package ru.otus.hw.services;

import ru.otus.hw.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(long id);

    List<Comment> findAllForBook(long bookId);

    Comment create(String text, long bookId);

    Comment update(long id, String text, long bookId);

    void deleteById(long id);
}

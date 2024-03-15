package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.models.Comment;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAllForBook(long bookId) {
        return commentRepository.findAllForBook(bookId);
    }

    @Transactional
    @Override
    public Comment create(String text, long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id %d not found".formatted(bookId)));
        var comment = new Comment(0L, text, book);
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public Comment update(long id, String text, long bookId) {
        if (commentRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(
                    "Не удалось найти комментарий с id = %d".formatted(id));
        }

        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id %d not found".formatted(bookId)));
        var comment = new Comment(id, text, book);
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}

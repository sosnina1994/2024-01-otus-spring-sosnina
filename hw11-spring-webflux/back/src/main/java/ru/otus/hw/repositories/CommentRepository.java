package ru.otus.hw.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.hw.models.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
    Flux<Comment> findAllByBookId(String bookId);
}

package ru.otus.hw.repositories;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw.models.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findById(String id);

    @Aggregation(pipeline = { "{ '$group' : { '_id': '$author._id', author: { '$first': '$author' } } }" })
    List<Book> findDistinctAuthors();
}

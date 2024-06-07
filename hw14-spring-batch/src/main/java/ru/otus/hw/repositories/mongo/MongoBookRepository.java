package ru.otus.hw.repositories.mongo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw.models.mongo.MongoBook;

import java.util.List;
import java.util.Optional;


public interface MongoBookRepository extends MongoRepository<MongoBook, String> {
    Optional<MongoBook> findById(String id);

    @Aggregation(pipeline = { "{ '$group' : { '_id': '$author._id', author: { '$first': '$author' } } }" })
    List<MongoBook> findDistinctAuthors();

    @Cacheable(value = "books", key = "#title")
    List<MongoBook> findByTitle(String title);
}

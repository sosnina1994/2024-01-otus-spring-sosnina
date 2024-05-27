package ru.otus.hw.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw.models.mongo.MongoAuthor;

import java.util.List;

public interface MongoAuthorRepository extends MongoRepository<MongoAuthor, String> {
    List<MongoAuthor> findAll();
}

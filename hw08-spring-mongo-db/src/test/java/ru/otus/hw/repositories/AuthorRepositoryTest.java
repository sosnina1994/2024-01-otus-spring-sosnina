package ru.otus.hw.repositories;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе MongoDB для работы с авторами")
@DataMongoTest
public class AuthorRepositoryTest {
    private static final String FIRST_AUTHOR_ID = "1";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void initialize() {
        mongoTemplate.save(new Author(FIRST_AUTHOR_ID, "Author"));
    }

    @DisplayName("Получение автора по id")
    @Test
    void findById() {
        val optionalActualGenre = authorRepository.findById(FIRST_AUTHOR_ID);
        val expectedGenre = mongoTemplate.findById(FIRST_AUTHOR_ID, Author.class);

        assertThat(optionalActualGenre).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
    }
}

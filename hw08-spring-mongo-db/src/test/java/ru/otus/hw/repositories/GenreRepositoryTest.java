package ru.otus.hw.repositories;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе MongoDB для работы с жанрами")
@DataMongoTest
public class GenreRepositoryTest {

    private static final String FIRST_GENRE_ID = "1";

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void initialize() {
        mongoTemplate.save(new Genre(FIRST_GENRE_ID, "Genre"));
    }


    @DisplayName("Получение жанра по id")
    @Test
    void findById() {
        val optionalActualGenre = genreRepository.findById(FIRST_GENRE_ID);
        val expectedGenre = mongoTemplate.findById(FIRST_GENRE_ID, Genre.class);

        assertThat(optionalActualGenre).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
    }
}

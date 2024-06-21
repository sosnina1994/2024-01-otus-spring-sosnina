package ru.otus.hw.repositories;

import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import ru.otus.hw.models.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Репозиторий на основе JPA для работы с жанрами")
@DataJpaTest
class JpaGenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    private static final long FIRST_GENRE_ID = 1L;

    @DisplayName("Получение списка жанров")
    @Test
    void shouldFindAllGanges() {
        var findGenres = genreRepository.findAll();
        var expectedGenres = getGenreDb();

        assertThat(findGenres).containsExactlyElementsOf(expectedGenres);
        findGenres.forEach(System.out::println);
    }

    private List<Genre> getGenreDb() {
        return IntStream.range(1, 4).boxed()
                .map(id -> em.find(Genre.class, id))
                .toList();
    }

    @DisplayName("Получение жанра по id")
    @Test
    void shouldFindGenreById() {
        val optionalGenre = genreRepository.findById(FIRST_GENRE_ID);
        val expectedGenre = em.find(Genre.class, FIRST_GENRE_ID);

        assertThat(optionalGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}
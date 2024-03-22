package ru.otus.hw.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе JPA для работы с жанрами")
@DataJpaTest
@Import(JpaGenreRepository.class)
class JpaGenreRepositoryTest {

    @Autowired
    private JpaGenreRepository jpaGenreRepository;

    @Autowired
    private TestEntityManager em;

    private static final long FIRST_GENRE_ID = 1L;

    @DisplayName("Получение списка жанров")
    @Test
    void shouldFindAllGanges() {
        var findGenres = jpaGenreRepository.findAll();
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
        val optionalGenre = jpaGenreRepository.findById(FIRST_GENRE_ID);
        val expectedGenre = em.find(Genre.class, FIRST_GENRE_ID);

        assertThat(optionalGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}
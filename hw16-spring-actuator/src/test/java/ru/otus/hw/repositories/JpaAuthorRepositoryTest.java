package ru.otus.hw.repositories;

import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import ru.otus.hw.models.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Репозиторий на основе JPA для работы с авторами")
@DataJpaTest
class JpaAuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    private static final long FIRST_AUTHOR_ID = 1L;

    @DisplayName("Получение списка всех авторов")
    @Test
    void shouldFindAllAuthors() {
        var findAuthors = authorRepository.findAll();
        var expectedAuthors = getAuthorDb();

        assertThat(findAuthors).containsExactlyElementsOf(expectedAuthors);
        findAuthors.forEach(System.out::println);
    }

    private List<Author> getAuthorDb() {
        return IntStream.range(1, 4).boxed()
                .map(id -> em.find(Author.class, id))
                .toList();
    }

    @DisplayName("Получение автора по id")
    @Test
    void shouldGetAuthorsById() {
        val findAuthor = authorRepository.findById(FIRST_AUTHOR_ID);
        val expectedAuthor = em.find(Author.class, FIRST_AUTHOR_ID);

        assertThat(findAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}
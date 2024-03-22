package ru.otus.hw.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе JPA для работы с книгами")
@DataJpaTest
@Import({JpaBookRepository.class})
class JpaBookRepositoryTest {

    private static final long FIRST_AUTHOR_ID = 1L;
    private static final long FIRST_GENRE_ID = 1L;
    private static final long FIRST_BOOK_ID = 1L;

    @Autowired
    private JpaBookRepository bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;


    @DisplayName("Получение книги по id")
    @Test
    void shouldFindBookById() {
        var expectedBooks = getDbBooks();
        for (Book expectedBook : expectedBooks) {
            var actualBook = bookRepositoryJpa.findById(expectedBook.getId());
            assertThat(actualBook).isPresent()
                    .get()
                    .isEqualTo(expectedBook);
        }
    }

    @DisplayName("Получение списка книг")
    @Test
    void shouldFindAllBooks() {
        var actualBooks = bookRepositoryJpa.findAll();
        var expectedBooks = getDbBooks();

        assertThat(actualBooks).containsExactlyElementsOf(expectedBooks);
        actualBooks.forEach(System.out::println);
    }

    @DisplayName("Сохранение новой книги")
    @Test
    void shouldSaveNewBook() {
        val author = em.find(Author.class, FIRST_AUTHOR_ID);
        val genre = em.find(Genre.class, FIRST_GENRE_ID);
        val addedBook = new Book(0L, "BookTitle_NEW", author, genre);
        em.merge(addedBook);

        bookRepositoryJpa.save(addedBook);
        em.detach(addedBook);
        assertThat(addedBook.getId()).isGreaterThan(0);

        val findBook = em.find(Book.class, addedBook.getId());
        assertThat(findBook)
                .usingRecursiveComparison()
                .isEqualTo(addedBook);
    }

    @DisplayName("Обновление книги")
    @Test
    void shouldSaveUpdatedBook() {
        val author = em.find(Author.class, FIRST_AUTHOR_ID);
        val genre = em.find(Genre.class, FIRST_GENRE_ID);

        var expectedBook = new Book(FIRST_BOOK_ID, "BookTitle_NEW", author, genre);
        var currentBook = em.find(Book.class, expectedBook.getId());

        assertThat(currentBook)
                .usingRecursiveComparison()
                .isNotEqualTo(expectedBook);

        var returnedBook = bookRepositoryJpa.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        var findBook = em.find(Book.class, returnedBook.getId());
        assertThat(findBook)
                .usingRecursiveComparison()
                .isEqualTo(returnedBook);
    }

    @DisplayName("Удаление книги по id ")
    @Test
    void shouldDeleteBook() {
        Book firstBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        bookRepositoryJpa.deleteById(FIRST_BOOK_ID);
        Book notFoundBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(notFoundBook).isNull();
    }

    private List<Book> getDbBooks() {
        return IntStream.range(1, 4).boxed()
                .map(id -> em.find(Book.class, id))
                .toList();
    }

}

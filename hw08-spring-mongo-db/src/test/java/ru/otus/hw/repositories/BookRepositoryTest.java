package ru.otus.hw.repositories;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе MongoDB для работы с книгами")
@DataMongoTest
class BookRepositoryTest {

    private static final String FIRST_AUTHOR_ID = "1";
    private static final String FIRST_GENRE_ID = "1";
    private static final String FIRST_BOOK_ID = "1";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void initialize() {
        final Author author_1 = new Author(FIRST_AUTHOR_ID, "Станислав Лем");
        final Genre genre_1 = new Genre(FIRST_GENRE_ID, "Научная фантастика");
        mongoTemplate.save(new Book(FIRST_BOOK_ID, "Эдем", author_1, genre_1));

        final Author author_2 = new Author("2", "Теодор Драйзер");
        final Genre genre_2 = new Genre("2", "Социальная драма");
        mongoTemplate.save(new Book("2", "Американская трагедия", author_2, genre_2));
    }

    @DisplayName("Получение книги по id")
    @Test
    void shouldReturnCorrectBookById() {
        var expectedBooks = getDbBooks();
        for (Book expectedBook : expectedBooks) {
            var actualBookOption = bookRepository.findById(expectedBook.getId());

            assertThat(actualBookOption).isPresent()
                    .get()
                    .usingRecursiveComparison()
                    .isEqualTo(expectedBook);
        }
    }

    @DisplayName("Сохранение новой книги")
    @Test
    void shouldSaveNewBook() {
        val author = mongoTemplate.findById(FIRST_AUTHOR_ID, Author.class);
        val genre = mongoTemplate.findById(FIRST_GENRE_ID, Genre.class);
        val addedBook = new Book(null, "BookTitle_10500", author, genre);

        bookRepository.save(addedBook);
        assertThat(addedBook.getId()).isNotEmpty();

        val findBook = mongoTemplate.findById(addedBook.getId(), Book.class);
        assertThat(findBook)
                .usingRecursiveComparison()
                .isEqualTo(addedBook);
    }

    @DisplayName("Обновление книги")
    @Test
    void shouldSaveUpdatedBook() {
        val author = mongoTemplate.findById(FIRST_AUTHOR_ID, Author.class);
        val genre = mongoTemplate.findById(FIRST_GENRE_ID, Genre.class);

        var expectedBook = new Book(FIRST_BOOK_ID, "BookTitle_10500", author, genre);
        var currentBook = mongoTemplate.findById(expectedBook.getId(), Book.class);

        assertThat(currentBook)
                .usingRecursiveComparison()
                .isNotEqualTo(expectedBook);

        var returnedBook = bookRepository.save(expectedBook);
        assertThat(returnedBook).isNotNull()
                .matches(book -> !book.getId().isEmpty())
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedBook);

        var findBook = mongoTemplate.findById(returnedBook.getId(), Book.class);
        assertThat(findBook)
                .usingRecursiveComparison()
                .isEqualTo(returnedBook);
    }

    private List<Book> getDbBooks() {
        return mongoTemplate.findAll(Book.class);
    }
}
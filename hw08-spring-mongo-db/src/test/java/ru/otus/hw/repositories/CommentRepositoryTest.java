package ru.otus.hw.repositories;

import lombok.val;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе MongoDB для работы с комментариями")
@DataMongoTest
public class CommentRepositoryTest {
    private static final String FIRST_COMMENT_ID = "1";

    private static final String BOOK_FIRST_ID = "1";
    private static final String BOOK_SECOND_ID = "2";

    private static final String NEW_COMMENT_TEXT = "Test comment";

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void initialize() {
        Book firstBook = new Book(BOOK_FIRST_ID, "Book 1", null, null);
        mongoTemplate.save(firstBook);
        Book secondBook = new Book(BOOK_SECOND_ID, "Book 2", null, null);
        mongoTemplate.save(secondBook);

        mongoTemplate.save(new Comment(FIRST_COMMENT_ID, "1", firstBook));
    }


    @DisplayName("Сохранение комментария к книге")
    @Test
    void shouldAddNewComment() {
        Book firstBook = mongoTemplate.findById(BOOK_FIRST_ID, Book.class);

        Comment addedComment = new Comment(null, NEW_COMMENT_TEXT, firstBook);
        commentRepository.save(addedComment);
        Comment findComment = mongoTemplate.findById(addedComment.getId(), Comment.class);

        assertThat(addedComment)
                .usingRecursiveComparison()
                .isEqualTo(findComment);
    }

    @DisplayName("Изменение комментария")
    @Test
    void shouldChangeComment() {
        Book secondBook = mongoTemplate.findById(BOOK_SECOND_ID, Book.class);
        var previousComment = mongoTemplate.findById(FIRST_COMMENT_ID, Comment.class);

        var updatedComment = commentRepository.save(
                new Comment(FIRST_COMMENT_ID, NEW_COMMENT_TEXT, secondBook));
        var findComment = mongoTemplate.findById(FIRST_COMMENT_ID, Comment.class);

        assertThat(updatedComment)
                .usingRecursiveComparison()
                .isNotEqualTo(previousComment)
                .isEqualTo(findComment);
    }

    @DisplayName("Получение комментария по id")
    @Test
    void shouldFindExpectedCommentById() {
        val optionalActualComment = commentRepository.findById(FIRST_COMMENT_ID);
        val expectedComment = mongoTemplate.findById(FIRST_COMMENT_ID, Comment.class);

        assertThat(optionalActualComment).isPresent().get()
                .usingRecursiveComparison(comparisonConfiguration())
                .isEqualTo(expectedComment);
    }

    private RecursiveComparisonConfiguration comparisonConfiguration() {
        Comparator<Book> bookComparator = Comparator.comparing(book -> String.valueOf(book.getId()));
        return RecursiveComparisonConfiguration.builder()
                .withComparatorForType(bookComparator, Book.class)
                .build();
    }
}

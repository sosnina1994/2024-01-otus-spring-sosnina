package ru.otus.hw.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.CommentRepository;

@ChangeLog
public class DatabaseChangelog {
    private int commentId = 1;

    @ChangeSet(order = "001", id = "dropDataBase", author = "sosnina", runAlways = true)
    public void dropTables(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", id = "addCommentForFirstBook", author = "sosnina")
    public void addCommentForFirstBook(CommentRepository commentRepository) {
        final Author author = new Author("1", "Станислав Лем");
        final Genre genre = new Genre("1", "Научная фантастика");
        final Book book = new Book("1", "Эдем", author, genre);

        commentRepository.save(
                new Comment(String.valueOf(commentId++), "Динамический сюжет и интересные персонажи", book));
    }

    @ChangeSet(order = "003", id = "addCommentForSecondBook", author = "sosnina")
    public void addCommentForSecondBook(CommentRepository commentRepository) {
        final Author author = new Author("2", "Теодор Драйзер");
        final Genre genre = new Genre("2", "Социальная драма");
        final Book book = new Book("2", "Американская трагедия", author, genre);

        commentRepository.save(new Comment(String.valueOf(commentId++), "Любимая книга после Гэтсби", book));
    }

    @ChangeSet(order = "004", id = "addCommentForThirdBook", author = "sosnina")
    public void addCommentForThirdBook(CommentRepository commentRepository) {
        final Author author = new Author("3", "Стивен Кинг");
        final Genre genre = new Genre("3", "Психологический триллер");
        final Book book = new Book("3", "Мизери", author, genre);

        commentRepository.save(new Comment(String.valueOf(commentId), "Слишком реалистично", book));
    }
}
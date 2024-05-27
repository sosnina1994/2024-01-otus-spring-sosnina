package ru.otus.hw.processors;

import jakarta.annotation.Nonnull;
import org.springframework.batch.item.ItemProcessor;
import ru.otus.hw.models.jpa.JpaBook;
import ru.otus.hw.models.mongo.MongoAuthor;
import ru.otus.hw.models.mongo.MongoBook;
import ru.otus.hw.models.mongo.MongoGenre;

public class BookProcessor implements ItemProcessor<JpaBook, MongoBook> {
    @Override
    public MongoBook process(@Nonnull JpaBook item) {
        AuthorProcessor authorProcessor = new AuthorProcessor();
        MongoAuthor author = authorProcessor.process(item.getAuthor());

        GenreProcessor genreProcessor = new GenreProcessor();
        MongoGenre genre = genreProcessor.process(item.getGenre());

        return new MongoBook(String.valueOf(item.getId()), item.getTitle(), author, genre);
    }
}

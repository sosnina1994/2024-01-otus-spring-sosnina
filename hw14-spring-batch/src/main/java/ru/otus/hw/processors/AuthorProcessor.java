package ru.otus.hw.processors;

import jakarta.annotation.Nonnull;
import org.springframework.batch.item.ItemProcessor;
import ru.otus.hw.models.jpa.JpaAuthor;
import ru.otus.hw.models.mongo.MongoAuthor;

public class AuthorProcessor implements ItemProcessor<JpaAuthor, MongoAuthor> {
    @Override
    public MongoAuthor process(@Nonnull JpaAuthor item) {
        return new MongoAuthor(String.valueOf(item.getId()), item.getFullName());
    }
}

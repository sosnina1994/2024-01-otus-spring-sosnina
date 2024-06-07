package ru.otus.hw.processors;

import jakarta.annotation.Nonnull;
import org.springframework.batch.item.ItemProcessor;
import ru.otus.hw.models.jpa.JpaComment;
import ru.otus.hw.models.mongo.MongoComment;

public class CommentProcessor implements ItemProcessor<JpaComment, MongoComment> {

    @Override
    public MongoComment process(@Nonnull JpaComment item) throws Exception {
        BookProcessor processor = new BookProcessor();
        return new MongoComment(String.valueOf(item.getId()), item.getText(),
                processor.process(item.getBook()));
    }
}

package ru.otus.hw.models.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class MongoComment {

    @Id
    private String id;

    private String text;

    @DBRef
    private MongoBook book;

}

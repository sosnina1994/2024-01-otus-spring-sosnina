package ru.otus.hw.models.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class MongoAuthor {

    @Id
    private String id;

    private String fullName;
}

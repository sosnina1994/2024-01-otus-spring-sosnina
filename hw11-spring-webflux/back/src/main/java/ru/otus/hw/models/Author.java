package ru.otus.hw.models;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Document(collection = "authors")
public class Author {

    @Id
    private String id;

    @NotNull
    private String fullName;

    @PersistenceCreator
    public Author(String id, @NotNull String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}

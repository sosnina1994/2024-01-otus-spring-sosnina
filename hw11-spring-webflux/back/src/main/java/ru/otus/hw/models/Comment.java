package ru.otus.hw.models;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @NotNull
    private String text;

    @NotNull
    @DBRef(lazy = true)
    private Book book;

    @PersistenceCreator
    public Comment(String id, @NotNull String text, @NotNull Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }
}


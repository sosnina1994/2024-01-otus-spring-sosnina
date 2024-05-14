package ru.otus.hw.models;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Document(collection = "genres")
public class Genre {

    @Id
    private String id;

    @NotNull
    private String name;

    @PersistenceCreator
    public Genre(String id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
}

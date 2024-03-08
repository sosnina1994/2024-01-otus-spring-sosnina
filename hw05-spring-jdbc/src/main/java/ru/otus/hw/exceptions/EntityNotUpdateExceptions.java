package ru.otus.hw.exceptions;

import ru.otus.hw.models.Book;

public class EntityNotUpdateExceptions extends RuntimeException {
    public EntityNotUpdateExceptions(Book book) {
        super(String.format("Книга %s не изменена!", book == null ? "" : book.getTitle()));
    }
}

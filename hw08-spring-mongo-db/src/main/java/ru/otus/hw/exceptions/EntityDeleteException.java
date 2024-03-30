package ru.otus.hw.exceptions;

public class EntityDeleteException extends RuntimeException {
    public EntityDeleteException(String errorMsg) {
        super(errorMsg);
    }
}

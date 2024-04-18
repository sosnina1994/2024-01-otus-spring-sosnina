package ru.otus.hw.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.hw.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundEx(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}

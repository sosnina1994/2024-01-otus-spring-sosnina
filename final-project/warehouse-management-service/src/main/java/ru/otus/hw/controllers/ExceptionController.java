package ru.otus.hw.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.hw.exceptions.NotFoundException;

import java.util.List;
import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundEx(NotFoundException ex) {
        log.warn(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public List<String> handleEntityValidateEx(MethodArgumentNotValidException ex) {
        final String[] errors = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
        log.warn(Arrays.toString(errors));
        return Arrays.stream(errors).toList();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleRuntimeException(RuntimeException ex) {
        String error = "Internal error occurred, " + ex.getStackTrace()[0];
        log.error(error);
        return error;
    }
}

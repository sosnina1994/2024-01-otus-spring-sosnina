package ru.otus.hw.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.dto.AuthorDto;
import ru.otus.hw.services.AuthorServiceImpl;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@CircuitBreaker(name = "authorBreaker", fallbackMethod = "unknownAuthorsFallback")
@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @GetMapping("/api/authors")
    public List<AuthorDto> allAuthorsList() {
        return authorService.findAll();
    }

    public List<AuthorDto> unknownAuthorsFallback(Exception ex) {
        log.error(ex.getMessage());
        return new ArrayList<>();
    }
}

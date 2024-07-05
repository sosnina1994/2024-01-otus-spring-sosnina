package ru.otus.hw.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.dto.GenreDto;
import ru.otus.hw.services.GenreServiceImpl;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@CircuitBreaker(name = "genreBreaker", fallbackMethod = "unknownGenreListFallback")
@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreServiceImpl genreService;

    @GetMapping("/api/genres")
    public List<GenreDto> allGenresList() {
        return genreService.findAll();
    }

    public List<GenreDto> unknownGenreListFallback(Exception ex) {
        log.error(ex.getMessage());
        return new ArrayList<>();
    }
}

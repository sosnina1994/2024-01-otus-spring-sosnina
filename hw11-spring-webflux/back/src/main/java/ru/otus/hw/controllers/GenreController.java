package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.hw.dto.GenreDto;
import ru.otus.hw.mappers.GenreMapper;
import ru.otus.hw.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreRepository genreRepository;

    private final GenreMapper mapper;

    @GetMapping("/api/genres")
    public Flux<GenreDto> allGenresList() {
        return genreRepository.findAll()
                .map(mapper::toDto);
    }
}

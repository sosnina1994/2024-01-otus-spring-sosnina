package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.hw.dto.AuthorDto;
import ru.otus.hw.mappers.AuthorMapper;
import ru.otus.hw.repositories.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    private final AuthorMapper mapper;

    @GetMapping("/api/authors")
    public Flux<AuthorDto> allAuthorsList() {
        return authorRepository.findAll()
                .map(mapper::toDto);
    }
}

package ru.otus.hw.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.hw.dto.GenreDto;

import java.util.List;

public interface GenreService {
    @PreAuthorize("hasRole('USER')")
    List<GenreDto> findAll();
}

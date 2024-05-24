package ru.otus.hw.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.hw.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    @PreAuthorize("hasRole('USER')")
    List<AuthorDto> findAll();
}

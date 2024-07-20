package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.hw.dto.ToolBrandDto;
import ru.otus.hw.services.ToolBrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToolBrandController {
    private final ToolBrandService toolBrandService;

    @PostMapping ("/api/tool-brands")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolBrandDto create(@Valid @RequestBody ToolBrandDto toolBrandDto) {
        return toolBrandService.create(toolBrandDto);
    }

    @GetMapping("/api/tool-brands")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ToolBrandDto> getAll() {
        return toolBrandService.findAll();
    }

}

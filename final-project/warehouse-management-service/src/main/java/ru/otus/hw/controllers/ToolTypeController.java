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
import ru.otus.hw.dto.ToolTypeDto;
import ru.otus.hw.services.ToolTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToolTypeController {
    private final ToolTypeService toolTypeService;

    @PostMapping ("/api/tool-types")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolTypeDto create(@Valid @RequestBody ToolTypeDto toolTypeDto) {
        return toolTypeService.create(toolTypeDto);
    }

    @GetMapping("/api/tool-types")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ToolTypeDto> getAll() {
        return toolTypeService.findAll();
    }

}

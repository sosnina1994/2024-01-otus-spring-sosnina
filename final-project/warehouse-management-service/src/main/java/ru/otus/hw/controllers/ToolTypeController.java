package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw.dto.ToolTypeDto;
import ru.otus.hw.services.ToolTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToolTypeController {
    private final ToolTypeService toolTypeService;

    @PostMapping ("/api/tool-types")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolTypeDto create(@Valid @RequestBody ToolTypeDto toolTypeDto) {
        return toolTypeService.create(toolTypeDto);
    }

    @GetMapping("/api/tool-types")
    public List<ToolTypeDto> getAll() {
        return toolTypeService.findAll();
    }

}

package ru.otus.hw.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.hw.dto.ToolTypeDto;
import ru.otus.hw.services.ToolTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ToolTypeController", description = "АПИ для управления типами инструмента")
public class ToolTypeController {
    private final ToolTypeService toolTypeService;

    @Operation(description = "Создание нового типа инструмента")
    @PostMapping ("/api/tool-types")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolTypeDto create(@Valid @RequestBody ToolTypeDto toolTypeDto) {
        return toolTypeService.create(toolTypeDto);
    }

    @Operation(description = "Получение типов инструмента")
    @GetMapping("/api/tool-types")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ToolTypeDto> getAll() {
        return toolTypeService.findAll();
    }

    @Operation(description = "Получение типа инструмента по идентификатору")
    @GetMapping("/api/tool-types/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ToolTypeDto getById(@PathVariable(value = "id") Long id) {
        return toolTypeService.getById(id);
    }

}

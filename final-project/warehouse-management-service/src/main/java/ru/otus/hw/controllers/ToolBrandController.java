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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolBrandDto;
import ru.otus.hw.services.ToolBrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ToolBrandController", description = "АПИ для управления брендами инструмента")
public class ToolBrandController {
    private final ToolBrandService toolBrandService;

    @Operation(description = "Создание нового бренда инструмента")
    @PostMapping("/api/tool-brands")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolBrandDto create(@Valid @RequestBody ToolBrandDto toolBrandDto) {
        return toolBrandService.create(toolBrandDto);
    }

    @Operation(description = "Создание списка брендов инструментов")
    @GetMapping("/api/tool-brands")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ToolBrandDto> getAll() {
        return toolBrandService.findAll();
    }

    @Operation(description = "Получение бренда инструмента по идентификатору")
    @GetMapping("/api/tool-brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ToolBrandDto getById(@PathVariable(value = "id") Long id) {
        return toolBrandService.findById(id);
    }

}

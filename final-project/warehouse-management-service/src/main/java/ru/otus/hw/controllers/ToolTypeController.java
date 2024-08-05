package ru.otus.hw.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ToolTypeController", description = "АПИ для управления типами инструмента")
public class ToolTypeController {

    private static final String CIRCUIT_BREAKER_NAME  = "toolTypeBreaker";

    private final ToolTypeService toolTypeService;

    @Operation(description = "Создание нового типа инструмента")
    @PreAuthorize("hasRole('ADMIN')")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolTypeFallback")
    @PostMapping ("/api/tool-types")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolTypeDto create(@Valid @RequestBody ToolTypeDto toolTypeDto) {
        return toolTypeService.create(toolTypeDto);
    }

    @Operation(description = "Получение типов инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolTypeListFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/tool-types")
    public List<ToolTypeDto> getAll() {
        return toolTypeService.findAll();
    }

    @Operation(description = "Получение типа инструмента по идентификатору")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolTypeFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/tool-types/{id}")
    public ToolTypeDto getById(@PathVariable(value = "id") Long id) {
        return toolTypeService.getById(id);
    }

    public ToolTypeDto unknownToolTypeFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ToolTypeDto();
    }

    public List<ToolTypeDto> unknownToolTypeListFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ArrayList<>();
    }

}

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolBrandDto;
import ru.otus.hw.services.ToolBrandService;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ToolBrandController", description = "АПИ для управления брендами инструмента")
public class ToolBrandController {

    private static final String CIRCUIT_BREAKER_NAME  = "toolBrandBreaker";

    private final ToolBrandService toolBrandService;

    @Operation(description = "Создание нового бренда инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolBrandFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/tool-brands")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolBrandDto create(@Valid @RequestBody ToolBrandDto toolBrandDto) {
        return toolBrandService.create(toolBrandDto);
    }

    @Operation(description = "Создание списка брендов инструментов")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolBrandListFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/tool-brands")
    public List<ToolBrandDto> getAll() {
        return toolBrandService.findAll();
    }

    @Operation(description = "Получение бренда инструмента по идентификатору")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolBrandFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/tool-brands/{id}")
    public ToolBrandDto getById(@PathVariable(value = "id") Long id) {
        return toolBrandService.findById(id);
    }

    public ToolBrandDto unknownToolBrandFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ToolBrandDto();
    }

    public List<ToolBrandDto> unknownToolBrandListFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ArrayList<>();
    }
}

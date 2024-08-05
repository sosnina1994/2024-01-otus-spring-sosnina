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
import ru.otus.hw.dto.ToolDto;
import ru.otus.hw.dto.ToolCreateDto;
import ru.otus.hw.services.ToolService;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ToolController", description = "АПИ для управления инструментами")
public class ToolController {

    private static final String CIRCUIT_BREAKER_NAME  = "toolBreaker";

    private final ToolService toolService;

    @Operation(description = "Создание нового инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/tools")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolDto create(@Valid @RequestBody ToolCreateDto toolCreateDto) {
        return toolService.create(toolCreateDto);
    }


    @Operation(description = "Получение списка инструментов")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolListFallback")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/api/tools")
    public List<ToolDto> getAll() {
        return toolService.findAll();
    }

    @Operation(description = "Получение инструмента по идентификатору")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolFallback")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/api/tools/{id}")
    public ToolDto getById(@PathVariable(value = "id") Long id) {
        return toolService.findById(id);
    }


    public ToolDto unknownToolFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ToolDto();
    }

    public List<ToolDto> unknownToolListFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ArrayList<>();
    }

}

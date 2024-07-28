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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.hw.dto.ToolIssueCreateDto;
import ru.otus.hw.dto.ToolIssueDto;
import ru.otus.hw.services.ToolIssueService;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ToolIssueController", description = "АПИ для управления выдачей инструментов")
public class ToolIssueController {

    private static final String CIRCUIT_BREAKER_NAME = "toolBalanceBreaker";

    private final ToolIssueService toolIssueService;

    @Operation(description = "Создание выдачи инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolIssueFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/tool-issues")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolIssueDto save(@Valid @RequestBody ToolIssueCreateDto toolIssueCreateDto) {
        return toolIssueService.create(toolIssueCreateDto);
    }

    public ToolIssueDto unknownToolIssueFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ToolIssueDto();
    }
}

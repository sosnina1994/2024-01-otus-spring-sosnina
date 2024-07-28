package ru.otus.hw.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolIssueActDto;
import ru.otus.hw.services.ToolIssueActService;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ToolIssueActController", description = "АПИ для получении актов выдачи инструментов")
public class ToolIssueActController {

    private static final String CIRCUIT_BREAKER_NAME = "toolIssueActBreaker";

    private final ToolIssueActService issueActService;


    @Operation(description = "Получение всей истории выдачи инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolIssueActsFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/issue-tools")
    public List<ToolIssueActDto> getIssueActs() {
        return issueActService.findAll();
    }

    @Operation(description = "Получение акта выдачи инструмента по идентификатору")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolIssueActFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/issue-tools/{id}")
    public ToolIssueActDto getIssueActById(@PathVariable(value = "id") Long id) {
        return issueActService.findById(id);
    }

    public ToolIssueActDto unknownToolIssueActFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ToolIssueActDto();
    }

    public List<ToolIssueActDto> unknownToolIssueActsFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ArrayList<>();
    }
}

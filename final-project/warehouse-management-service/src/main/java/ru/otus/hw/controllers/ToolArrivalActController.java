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
import ru.otus.hw.dto.ToolArrivalActDto;
import ru.otus.hw.services.ToolArrivalActService;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ToolArrivalActController", description = "АПИ для получении актов пополнения инструментов")
public class ToolArrivalActController {

    private static final String CIRCUIT_BREAKER_NAME = "toolArrivalActBreaker";

    private final ToolArrivalActService arrivalActService;


    @Operation(description = "Получение всей истории пополнения инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolArrivalActsFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/arrival-tools")
    public List<ToolArrivalActDto> getIssueActs() {
        return arrivalActService.findAll();
    }

    @Operation(description = "Получение акта пополнения инструмента по идентификатору")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolArrivalActFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/arrival-tools/{id}")
    public ToolArrivalActDto getIssueActById(@PathVariable(value = "id") Long id) {
        return arrivalActService.findById(id);
    }

    public ToolArrivalActDto unknownToolArrivalActFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ToolArrivalActDto();
    }

    public List<ToolArrivalActDto> unknownToolArrivalActsFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ArrayList<>();
    }
}

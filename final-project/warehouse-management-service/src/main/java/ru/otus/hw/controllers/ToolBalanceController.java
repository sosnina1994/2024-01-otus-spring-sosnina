package ru.otus.hw.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolBalanceDto;
import ru.otus.hw.dto.ToolBalanceCreateDto;
import ru.otus.hw.services.ToolBalanceService;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ToolBalanceController", description = "АПИ для управления балансом инструментов")
public class ToolBalanceController {

    private static final String CIRCUIT_BREAKER_NAME = "toolBalanceBreaker";

    private final ToolBalanceService balanceService;

    @Operation(description = "Создание нового баланса для инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolBalanceFallback")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/tool-balances")
    public ToolBalanceDto create(@Valid @RequestBody ToolBalanceCreateDto toolBalanceCreateDto) {
        return balanceService.create(toolBalanceCreateDto);
    }

    @Operation(description = "Получение баланса инструмента")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolBalanceFallback")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/api/tool-balances/{toolId}")
    public ToolBalanceDto getByToolId(@PathVariable(value = "toolId") Long id) {
        return balanceService.getByToolId(id);
    }

    @Operation(description = "Получение балансов инструментов")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "unknownToolBalanceListFallback")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/api/tool-balances")
    public List<ToolBalanceDto> getAll() {
        return balanceService.findAll();
    }

    public ToolBalanceDto unknownToolBalanceFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ToolBalanceDto();
    }

    public List<ToolBalanceDto> unknownToolBalanceListFallback(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ArrayList<>();
    }

}

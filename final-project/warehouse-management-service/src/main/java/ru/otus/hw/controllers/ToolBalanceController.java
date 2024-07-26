package ru.otus.hw.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@Tag(name = "ToolBalanceController", description = "АПИ для управления балансом инструментов")
public class ToolBalanceController {

    private final ToolBalanceService balanceService;

    @Operation(description = "Создание нового баланса для инструмента")
    @PostMapping ("/api/tool-balances")
    @PreAuthorize("hasRole('ADMIN')")
    public ToolBalanceDto create(@Valid @RequestBody ToolBalanceCreateDto toolBalanceCreateDto) {
        return balanceService.create(toolBalanceCreateDto);
    }

    @Operation(description = "Получение баланса инструмента")
    @GetMapping("/api/tool-balances/{toolId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ToolBalanceDto getByToolId(@PathVariable(value = "toolId") Long id) {
        return balanceService.getByToolId(id);
    }

    @Operation(description = "Получение балансов инструментов")
    @GetMapping("/api/tool-balances")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ToolBalanceDto> getAll() {
        return balanceService.findAll();
    }

}

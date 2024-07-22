package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolBalanceDto;
import ru.otus.hw.services.ToolBalanceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final ToolBalanceService balanceService;

    @GetMapping("/api/tool-balances/{toolId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ToolBalanceDto getByToolId(@PathVariable(value = "toolId") Long id) {
        return balanceService.getByToolId(id);
    }

    @GetMapping("/api/tool-balances/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ToolBalanceDto> getAll() {
        return balanceService.findAll();
    }

}

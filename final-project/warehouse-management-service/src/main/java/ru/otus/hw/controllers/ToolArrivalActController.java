package ru.otus.hw.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolArrivalActDto;
import ru.otus.hw.services.ToolArrivalActService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ToolArrivalActController", description = "АПИ для получении актов пополнения инструментов")
public class ToolArrivalActController {
    private final ToolArrivalActService arrivalActService;


    @Operation(description = "Получение всей истории пополнения инструмента")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/arrival-tools")
    public List<ToolArrivalActDto> getIssueActs() {
        return arrivalActService.findAll();
    }

    @Operation(description = "Получение акта пополнения инструмента по идентификатору")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/arrival-tools/{id}")
    public ToolArrivalActDto getIssueActById(@PathVariable(value = "id") Long id) {
        return arrivalActService.findById(id);
    }
}

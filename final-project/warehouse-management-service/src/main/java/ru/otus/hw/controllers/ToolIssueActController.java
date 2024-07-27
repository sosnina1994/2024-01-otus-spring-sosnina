package ru.otus.hw.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolIssueActDto;
import ru.otus.hw.services.ToolIssueActService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ToolIssueActController", description = "АПИ для получении актов выдачи инструментов")
public class ToolIssueActController {
    private final ToolIssueActService issueActService;


    @Operation(description = "Получение всей истории выдачи инструмента")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/issue-tools")
    public List<ToolIssueActDto> getIssueActs() {
        return issueActService.findAll();
    }

    @Operation(description = "Получение акта выдачи инструмента по идентификатору")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reports/issue-tools/{id}")
    public ToolIssueActDto getIssueActById(@PathVariable(value = "id") Long id) {
        return issueActService.findById(id);
    }
}

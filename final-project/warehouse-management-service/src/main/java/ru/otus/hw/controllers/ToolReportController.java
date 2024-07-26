package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.IssueToolActDto;
import ru.otus.hw.services.ToolIssueActService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToolReportController {
    private final ToolIssueActService actService;

    @GetMapping("/api/reports/issue-tool")
    public List<IssueToolActDto> getActs() {
        return actService.findAll();
    }

    @GetMapping("/api/reports/issue-tool/{id}")
    public IssueToolActDto getActById(@PathVariable(value = "id") Long id) {
        return actService.findById(id);
    }
}

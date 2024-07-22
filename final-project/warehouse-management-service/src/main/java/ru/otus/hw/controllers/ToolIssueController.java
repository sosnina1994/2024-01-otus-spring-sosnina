package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw.dto.ToolIssueDto;
import ru.otus.hw.services.ToolIssueServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToolIssueController {
    private final ToolIssueServiceImpl toolIssueService;

    @GetMapping("/api/tool-issues")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ToolIssueDto> getAll() {
        return toolIssueService.findAll();
    }

}

package ru.otus.hw.services;

import ru.otus.hw.dto.ToolIssueDto;
import ru.otus.hw.dto.ToolIssueCreateDto;

public interface ToolIssueService {
    ToolIssueDto create(ToolIssueCreateDto toolIssueCreateDto);
}

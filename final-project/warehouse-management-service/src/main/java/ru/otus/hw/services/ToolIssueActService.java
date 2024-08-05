package ru.otus.hw.services;

import ru.otus.hw.dto.ToolIssueActDto;

import java.util.List;

public interface ToolIssueActService {
    List<ToolIssueActDto> findAll();

    ToolIssueActDto findById(Long id);
}

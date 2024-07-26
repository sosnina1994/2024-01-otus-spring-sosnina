package ru.otus.hw.services;

import ru.otus.hw.dto.IssueToolActDto;

import java.util.List;

public interface ToolIssueActService {
    List<IssueToolActDto> findAll();

    IssueToolActDto findById(Long id);
}

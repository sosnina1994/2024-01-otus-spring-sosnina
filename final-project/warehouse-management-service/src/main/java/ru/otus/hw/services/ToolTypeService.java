package ru.otus.hw.services;

import ru.otus.hw.dto.ToolTypeDto;

import java.util.List;

public interface ToolTypeService {
    List<ToolTypeDto> findAll();

    ToolTypeDto create(ToolTypeDto toolTypeDto);
}

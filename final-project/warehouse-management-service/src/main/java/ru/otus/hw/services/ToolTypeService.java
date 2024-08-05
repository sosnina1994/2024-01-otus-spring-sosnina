package ru.otus.hw.services;

import ru.otus.hw.dto.ToolTypeDto;

import java.util.List;

public interface ToolTypeService {
    List<ToolTypeDto> findAll();

    ToolTypeDto getById(Long id);

    ToolTypeDto create(ToolTypeDto toolTypeDto);
}

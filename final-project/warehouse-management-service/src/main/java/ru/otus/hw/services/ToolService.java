package ru.otus.hw.services;

import ru.otus.hw.dto.ToolDto;
import ru.otus.hw.dto.ToolCreateDto;
import ru.otus.hw.dto.ToolUpdateDto;

import java.util.List;

public interface ToolService {

    ToolDto create(ToolCreateDto toolCreateDto);

    ToolDto update(Long id, ToolUpdateDto bookDto);

    List<ToolDto> findAll();

    ToolDto findById(Long id);
}

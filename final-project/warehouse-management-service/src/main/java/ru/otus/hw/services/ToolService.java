package ru.otus.hw.services;

import ru.otus.hw.dto.ToolDto;
import ru.otus.hw.dto.ToolCreateDto;

import java.util.List;

public interface ToolService {

    ToolDto create(ToolCreateDto toolCreateDto);

    List<ToolDto> findAll();

    ToolDto findById(Long id);
}

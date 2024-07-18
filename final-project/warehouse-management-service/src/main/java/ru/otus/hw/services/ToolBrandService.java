package ru.otus.hw.services;

import ru.otus.hw.dto.ToolBrandDto;

import java.util.List;

public interface ToolBrandService {
    List<ToolBrandDto> findAll();

    ToolBrandDto create(ToolBrandDto toolBrandDto);
}

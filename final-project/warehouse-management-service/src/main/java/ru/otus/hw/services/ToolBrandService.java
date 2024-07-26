package ru.otus.hw.services;

import ru.otus.hw.dto.ToolBrandDto;

import java.util.List;

public interface ToolBrandService {
    List<ToolBrandDto> findAll();

    ToolBrandDto findById(Long id);

    ToolBrandDto create(ToolBrandDto toolBrandDto);
}

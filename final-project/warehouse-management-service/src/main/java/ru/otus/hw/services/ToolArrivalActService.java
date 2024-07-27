package ru.otus.hw.services;

import ru.otus.hw.dto.ToolArrivalActDto;

import java.util.List;

public interface ToolArrivalActService {
    List<ToolArrivalActDto> findAll();

    ToolArrivalActDto findById(Long id);
}

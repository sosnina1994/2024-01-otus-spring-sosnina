package ru.otus.hw.services;

import ru.otus.hw.dto.ToolBalanceDto;
import ru.otus.hw.dto.ToolBalanceCreateDto;

import java.util.List;

public interface ToolBalanceService {
    ToolBalanceDto create(ToolBalanceCreateDto toolBalanceCreateDto);

    ToolBalanceDto getByToolId(Long id);

    List<ToolBalanceDto> findAll();
}

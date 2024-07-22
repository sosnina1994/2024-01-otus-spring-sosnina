package ru.otus.hw.services;

import ru.otus.hw.dto.ToolBalanceDto;

import java.util.List;

public interface ToolBalanceService {
    ToolBalanceDto getByToolId(Long id);

    List<ToolBalanceDto> findAll();
}

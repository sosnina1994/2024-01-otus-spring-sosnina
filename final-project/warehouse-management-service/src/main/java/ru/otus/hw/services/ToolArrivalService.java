package ru.otus.hw.services;

import ru.otus.hw.dto.ToolArrivalDto;
import ru.otus.hw.dto.ToolArrivalCreateDto;

public interface ToolArrivalService {
    ToolArrivalDto create(ToolArrivalCreateDto toolArrivalCreateDto);
}

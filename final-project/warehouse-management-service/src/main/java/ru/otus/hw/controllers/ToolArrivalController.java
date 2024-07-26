package ru.otus.hw.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.hw.dto.ToolArrivalDto;
import ru.otus.hw.dto.ToolArrivalCreateDto;
import ru.otus.hw.services.ToolArrivalService;

@RestController
@RequiredArgsConstructor
public class ToolArrivalController {

    private final ToolArrivalService toolArrivalService;

    @Operation(description = "Приход инструмента")
    @PostMapping("/api/tool-arrivals")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolArrivalDto save(@Valid @RequestBody ToolArrivalCreateDto toolArrivalCreateDto) {
        return toolArrivalService.create(toolArrivalCreateDto);
    }
}

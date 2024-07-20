package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.dto.ToolDto;
import ru.otus.hw.dto.ToolCreateDto;
import ru.otus.hw.dto.ToolUpdateDto;
import ru.otus.hw.services.ToolService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;

    @PostMapping("/api/tools")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ToolDto create(@Valid @RequestBody ToolCreateDto toolCreateDto) {
        return toolService.create(toolCreateDto);
    }

    @PatchMapping("/api/tools/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ToolDto update(@PathVariable("id") Long id,
                          @Valid @RequestBody ToolUpdateDto toolUpdateDto) {
        return toolService.update(id, toolUpdateDto);
    }

    @GetMapping("/api/tools")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ToolDto> getAll() {
        return toolService.findAll();
    }

    @GetMapping("/api/tools/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ToolDto getById(@PathVariable(value = "id") Long id) {
        return toolService.findById(id);
    }

}

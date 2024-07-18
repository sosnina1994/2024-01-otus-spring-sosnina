package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolCreateDto;
import ru.otus.hw.dto.ToolDto;
import ru.otus.hw.dto.ToolUpdateDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolMapper;
import ru.otus.hw.repositories.ToolRepository;
import ru.otus.hw.repositories.ToolBrandRepository;
import ru.otus.hw.repositories.ToolTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements ToolService {
    private final ToolTypeRepository toolTypeRepository;

    private final ToolBrandRepository toolBrandRepository;

    private final ToolRepository toolRepository;

    private final ToolMapper toolMapper;

    @Override
    @Transactional
    public ToolDto create(ToolCreateDto toolDto) {
        final Long toolTypeId = toolDto.getTypeId();
        final Long brandId = toolDto.getBrandId();

        var toolType = toolTypeRepository.findById(toolTypeId)
                .orElseThrow(() -> new NotFoundException("Tool type with id %d not found".formatted(toolTypeId)));

        var toolBrand = toolBrandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Tool brand with id %d not found".formatted(brandId)));

        var tool = toolMapper.mapToModel(toolDto, toolType, toolBrand);
        return toolMapper.mapToDto(toolRepository.save(tool));
    }

    @Override
    @Transactional
    public ToolDto update(Long id, ToolUpdateDto toolUpdateDto) {
        var tool = toolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Toll with id %d not found".formatted(id)));

        tool.setBalance(toolUpdateDto.getBalance());
        tool.setMinBalance(toolUpdateDto.getMinBalance());

        return toolMapper.mapToDto(toolRepository.save(tool));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ToolDto> findAll() {
        return toolRepository.findAll().stream()
                .map(toolMapper::mapToDto)
                .toList();
    }

    @Override
    public ToolDto findById(Long id) {
        val tool = toolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tool with id = %d is not found".formatted(id)));
        return toolMapper.mapToDto(tool);
    }
}

package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolTypeDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolTypeMapper;
import ru.otus.hw.repositories.ToolTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolTypeServiceImp implements ToolTypeService {

    private final ToolTypeRepository toolTypeRepository;

    private final ToolTypeMapper toolTypeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ToolTypeDto> findAll() {
        return toolTypeRepository.findAll()
                .stream()
                .map(toolTypeMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ToolTypeDto getById(Long id) {
        val toolType = toolTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tool type with id = %d is not found".formatted(id)));
        return toolTypeMapper.mapToDto(toolType);
    }

    @Override
    @Transactional
    public ToolTypeDto create(ToolTypeDto toolTypeDto) {
        var model = toolTypeRepository.save(toolTypeMapper.mapToModel(toolTypeDto));
        return toolTypeMapper.mapToDto(model);
    }
}

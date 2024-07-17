package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolTypeDto;
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
    @Transactional
    public ToolTypeDto create(ToolTypeDto toolTypeDto) {
        var model = toolTypeRepository.save(toolTypeMapper.mapToModel(toolTypeDto));
        return toolTypeMapper.mapToDto(model);
    }
}

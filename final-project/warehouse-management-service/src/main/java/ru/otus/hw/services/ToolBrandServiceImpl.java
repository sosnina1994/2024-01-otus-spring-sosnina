package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolBrandDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolBrandMapper;
import ru.otus.hw.repositories.ToolBrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolBrandServiceImpl implements ToolBrandService {

    private final ToolBrandRepository toolBrandRepository;

    private final ToolBrandMapper toolBrandMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ToolBrandDto> findAll() {
        return toolBrandRepository.findAll()
                .stream()
                .map(toolBrandMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ToolBrandDto findById(Long id) {
        val toolType = toolBrandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tool brand with id = %d is not found".formatted(id)));
        return toolBrandMapper.mapToDto(toolType);
    }

    @Override
    @Transactional
    public ToolBrandDto create(ToolBrandDto toolBrandDto) {
        var model = toolBrandRepository.save(toolBrandMapper.mapToModel(toolBrandDto));
        return toolBrandMapper.mapToDto(model);
    }
}

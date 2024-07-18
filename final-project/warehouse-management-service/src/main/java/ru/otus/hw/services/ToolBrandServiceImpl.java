package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolBrandDto;
import ru.otus.hw.mappers.ManufacturerMapper;
import ru.otus.hw.repositories.ToolBrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolBrandServiceImpl implements ToolBrandService {

    private final ToolBrandRepository toolBrandRepository;

    private final ManufacturerMapper manufacturerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ToolBrandDto> findAll() {
        return toolBrandRepository.findAll()
                .stream()
                .map(manufacturerMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional
    public ToolBrandDto create(ToolBrandDto toolBrandDto) {
        var model = toolBrandRepository.save(manufacturerMapper.mapToModel(toolBrandDto));
        return manufacturerMapper.mapToDto(model);
    }
}

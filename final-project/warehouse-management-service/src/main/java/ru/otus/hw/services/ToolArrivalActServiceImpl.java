package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolArrivalActDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolArrivalActMapper;
import ru.otus.hw.repositories.ToolArrivalActRepository;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ToolArrivalActServiceImpl implements ToolArrivalActService {

    private final ToolArrivalActRepository toolArrivalActRepository;

    private final ToolArrivalActMapper toolArrivalActMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ToolArrivalActDto> findAll() {
        val entities = toolArrivalActRepository.findAll();
        val toolArrivalActs = new ArrayList<ToolArrivalActDto>();

        entities.forEach(entity -> toolArrivalActs.add(toolArrivalActMapper.mapToDto(entity)));

        return toolArrivalActs;
    }

    @Override
    @Transactional(readOnly = true)
    public ToolArrivalActDto findById(Long id) {
        val entityAct = toolArrivalActRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tool arrival with id = %d is not found".formatted(id)));
        return toolArrivalActMapper.mapToDto(entityAct);
    }
}

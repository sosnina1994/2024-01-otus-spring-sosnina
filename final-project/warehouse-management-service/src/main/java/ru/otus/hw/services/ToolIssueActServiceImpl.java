package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolIssueActDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolIssueActMapper;
import ru.otus.hw.repositories.ToolIssueActRepository;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ToolIssueActServiceImpl implements ToolIssueActService {

    private final ToolIssueActRepository repository;

    private final ToolIssueActMapper toolIssueActMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ToolIssueActDto> findAll() {
        val entities = repository.findAll();
        val toolIssueActs = new ArrayList<ToolIssueActDto>();
        entities.forEach(entity -> toolIssueActs.add(toolIssueActMapper.mapToDto(entity)));

        return toolIssueActs;
    }

    @Override
    @Transactional(readOnly = true)
    public ToolIssueActDto findById(Long id) {
        val entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tool issue act with id %d not found".formatted(id)));
        return toolIssueActMapper.mapToDto(entity);
    }
}

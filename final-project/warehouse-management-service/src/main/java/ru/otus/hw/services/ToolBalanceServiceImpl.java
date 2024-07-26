package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolBalanceCreateDto;
import ru.otus.hw.dto.ToolBalanceDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolBalanceMapper;
import ru.otus.hw.models.ToolBalance;
import ru.otus.hw.repositories.ToolRepository;
import ru.otus.hw.repositories.ToolBalanceRepository;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ToolBalanceServiceImpl implements ToolBalanceService {
    private final ToolRepository toolRepository;

    private final ToolBalanceRepository toolBalanceRepository;

    private final ToolBalanceMapper toolBalanceMapper;

    @Override
    @Transactional
    public ToolBalanceDto create(ToolBalanceCreateDto toolBalanceCreateDto) {
        val tool = toolRepository.findById(toolBalanceCreateDto.getToolId())
                .orElseThrow(() -> new NotFoundException("Tool with id = %d is not found"
                        .formatted(toolBalanceCreateDto.getToolId())));

        val balance = new ToolBalance();
        balance.setTool(tool);
        balance.setCurrentBalance(0);
        balance.setMinBalance(toolBalanceCreateDto.getMinBalance());
        val savedToolBalance = toolBalanceRepository.save(balance);
        return toolBalanceMapper.mapToDto(savedToolBalance);
    }

    @Override
    @Transactional(readOnly = true)
    public ToolBalanceDto getByToolId(Long id) {
        val balance = toolBalanceRepository.findToolBalancesByToolId(id)
                .orElseThrow(() -> new NotFoundException("Tool balance for tool with id = %d is not found"
                        .formatted(id)));

        return toolBalanceMapper.mapToDto(balance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ToolBalanceDto> findAll() {
        val balances = toolBalanceRepository.findAll();

        val dto = new ArrayList<ToolBalanceDto>();
        balances.forEach(toolBalance -> dto.add(toolBalanceMapper.mapToDto(toolBalance)));

        return dto;
    }

}

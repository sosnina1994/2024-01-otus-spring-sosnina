package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolBalanceDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolBalanceMapper;
import ru.otus.hw.repositories.ToolBalanceRepository;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ToolBalanceServiceImpl implements ToolBalanceService {
    private final ToolBalanceRepository toolBalanceRepository;

    private final ToolBalanceMapper toolBalanceMapper;

    @Override
    @Transactional(readOnly = true)
    public ToolBalanceDto getByToolId(Long id) {
        val balance = toolBalanceRepository.findToolBalancesByToolId(id)
                .orElseThrow(() -> new NotFoundException("Tool with id = %d is not found".formatted(id)));

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

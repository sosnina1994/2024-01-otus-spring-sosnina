package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolArrivalDto;
import ru.otus.hw.dto.ToolArrivalCreateDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.ToolArrivalMapper;
import ru.otus.hw.models.ToolArrivalAct;
import ru.otus.hw.models.ToolBalance;
import ru.otus.hw.repositories.ToolBalanceRepository;
import ru.otus.hw.repositories.ToolArrivalRepository;

@Service
@RequiredArgsConstructor
public class ToolArrivalServiceImpl implements ToolArrivalService {

    private final ToolBalanceRepository toolBalanceRepository;

    private final ToolArrivalRepository toolArrivalRepository;

    private final ToolArrivalMapper toolArrivalMapper;

    @Override
    @Transactional
    public ToolArrivalDto create(ToolArrivalCreateDto toolArrivalCreateDto) {
        val toolBalance = toolBalanceRepository
                .findToolBalancesByToolId(toolArrivalCreateDto.getToolId())
                .orElseThrow(() ->
                        new NotFoundException("Tool balance with id = %d is not found"
                                .formatted(toolArrivalCreateDto.getToolId())));

        val changingBalance = toolBalance.getCurrentBalance() + toolArrivalCreateDto.getCount();

        toolBalance.setCurrentBalance(changingBalance);
        toolBalanceRepository.save(toolBalance);

        val act = mapToAct(toolArrivalCreateDto, toolBalance);

        val toolArrivalAct = toolArrivalRepository.save(act);
        return toolArrivalMapper.mapToDto(toolArrivalAct);
    }

    private ToolArrivalAct mapToAct(ToolArrivalCreateDto toolArrivalCreateDto, ToolBalance balance) {
        val act = new ToolArrivalAct();
        act.setTool(balance.getTool());
        act.setCount(toolArrivalCreateDto.getCount());
        act.setOrderNumber(toolArrivalCreateDto.getOrderNumber());
        return act;
    }
}

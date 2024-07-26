package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolIssueCreateDto;
import ru.otus.hw.dto.ToolIssueDto;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.exceptions.InvalidDataException;
import ru.otus.hw.mappers.IssueToolMapper;
import ru.otus.hw.models.ToolBalance;
import ru.otus.hw.models.ToolIssueAct;
import ru.otus.hw.repositories.ToolBalanceRepository;
import ru.otus.hw.repositories.ToolIssueActRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ToolIssueServiceImpl implements ToolIssueService {
    private final ToolBalanceRepository toolBalanceRepository;

    private final ToolIssueActRepository issueActRepository;

    private final IssueToolMapper issueToolMapper;

    @Override
    @Transactional
    public ToolIssueDto create(ToolIssueCreateDto toolIssueCreateDto) {

        val toolBalance = toolBalanceRepository
                .findToolBalancesByToolId(toolIssueCreateDto.getToolId())
                .orElseThrow(() ->
                        new NotFoundException("Tool balance with id = %d is not found"
                                .formatted(toolIssueCreateDto.getToolId())));

        if (toolBalance.getCurrentBalance() < toolIssueCreateDto.getCount()) {
            throw new InvalidDataException("Current tool balance with id = %d less than transferred"
                    .formatted(toolIssueCreateDto.getToolId()));
        }
        val changingBalance = toolBalance.getCurrentBalance() - toolIssueCreateDto.getCount();

        toolBalance.setCurrentBalance(changingBalance);
        toolBalanceRepository.save(toolBalance);

        val act = mapToAct(toolIssueCreateDto, toolBalance);

        val issueToolAct = issueActRepository.save(act);
        return issueToolMapper.mapToDto(issueToolAct);
    }

    private ToolIssueAct mapToAct(ToolIssueCreateDto toolIssueCreateDto, ToolBalance balance) {
        val act = new ToolIssueAct();
        act.setTool(balance.getTool());
        act.setCount(toolIssueCreateDto.getCount());
        act.setRoutCardNumber(toolIssueCreateDto.getRoutCardNumber());
        act.setProductCipher(toolIssueCreateDto.getProductCipher());
        act.setOperationNumber(toolIssueCreateDto.getOperationNumber());
        act.setWorkplaceNumber(toolIssueCreateDto.getWorkplaceNumber());
        act.setEmployeeName(toolIssueCreateDto.getEmployeeName());
        act.setCreate(LocalDateTime.now());
        return act;
    }
}

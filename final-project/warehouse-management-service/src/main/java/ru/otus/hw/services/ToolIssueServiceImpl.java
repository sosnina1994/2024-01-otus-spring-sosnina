package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.ToolIssueDto;
import ru.otus.hw.models.ToolIssue;
import ru.otus.hw.repositories.ToolIssueRepository;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ToolIssueServiceImpl {
    private final ToolIssueRepository toolIssueRepository;

    @Transactional(readOnly = true)
    public List<ToolIssueDto> findAll() {
        val issues = toolIssueRepository.findAll();
        return map(issues);
    }

    private List<ToolIssueDto> map(List<ToolIssue> entities) {

        val issueDto = new ArrayList<ToolIssueDto>();

        entities.forEach(toolIssue -> {
                val dto = new ToolIssueDto()
                .setId(toolIssue.getId())
                .setRoutCardNumber(toolIssue.getRoutCardNumber())
                .setProductCipher(toolIssue.getProductCipher());
            issueDto.add(dto);
        });


        return issueDto;

    }
}

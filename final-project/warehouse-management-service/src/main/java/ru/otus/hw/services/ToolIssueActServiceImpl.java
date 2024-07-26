package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.IssueToolActDto;
import ru.otus.hw.mappers.ToolMapper;
import ru.otus.hw.models.ToolIssueAct;
import ru.otus.hw.repositories.ToolIssueActRepository;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ToolIssueActServiceImpl implements ToolIssueActService {

    private final ToolIssueActRepository repository;

    private final ToolMapper toolMapper;

    @Override
    @Transactional(readOnly = true)
    public List<IssueToolActDto> findAll() {
        val entities = repository.findAll();
        return mapToDto(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public IssueToolActDto findById(Long id) {
        val entity = repository.findById(id);
        return mapToDto(entity.stream().toList()).get(0);
    }


    private List<IssueToolActDto> mapToDto(List<ToolIssueAct> entities) {
        val list = new ArrayList<IssueToolActDto>();
        entities.forEach(entity -> {
            val dto = new IssueToolActDto()
                    .setId(entity.getId())
                    .setTool(toolMapper.mapToDto(entity.getTool()))
                    .setCount(entity.getCount())
                    .setRoutCardNumber(entity.getRoutCardNumber())
                    .setProductCipher(entity.getProductCipher())
                    .setOperationNumber(entity.getOperationNumber())
                    .setWorkplaceNumber(entity.getWorkplaceNumber())
                    .setEmployeeName(entity.getEmployeeName())
                    .setCreate(entity.getCreate());
            list.add(dto);
        });


        return list;
    }
}

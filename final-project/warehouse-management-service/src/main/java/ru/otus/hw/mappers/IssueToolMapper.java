package ru.otus.hw.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import ru.otus.hw.dto.ToolIssueDto;
import ru.otus.hw.models.ToolIssueAct;

@Mapper(componentModel = "spring")
public interface IssueToolMapper {

    @Mappings({
            @Mapping(target = "toolDto", source = "tool"),
            @Mapping(target = "toolDto.type", source = "tool.toolType"),
            @Mapping(target = "toolDto.brand", source = "tool.toolBrand")
    })
    ToolIssueDto mapToDto(ToolIssueAct toolIssueAct);
}

package ru.otus.hw.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.hw.dto.ToolIssueActDto;
import ru.otus.hw.models.ToolIssueAct;

@Mapper(componentModel = "spring")
public interface ToolIssueActMapper {
    @Mappings({
            @Mapping(target = "toolDto", source = "tool"),
            @Mapping(target = "toolDto.type", source = "tool.toolType"),
            @Mapping(target = "toolDto.brand", source = "tool.toolBrand")
    })
    ToolIssueActDto mapToDto(ToolIssueAct toolIssueAct);
}

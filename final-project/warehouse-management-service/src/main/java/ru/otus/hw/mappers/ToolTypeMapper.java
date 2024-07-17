package ru.otus.hw.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.dto.ToolTypeDto;
import ru.otus.hw.models.ToolType;

@Mapper(componentModel = "spring")
public interface ToolTypeMapper {
    ToolTypeDto mapToDto(ToolType toolType);

    @Mapping(target = "id", ignore = true)
    ToolType mapToModel(ToolTypeDto toolTypeDto);
}

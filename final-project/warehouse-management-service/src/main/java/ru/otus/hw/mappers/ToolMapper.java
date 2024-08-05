package ru.otus.hw.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.hw.dto.ToolCreateDto;
import ru.otus.hw.dto.ToolDto;
import ru.otus.hw.models.Tool;
import ru.otus.hw.models.ToolType;
import ru.otus.hw.models.ToolBrand;

@Mapper(componentModel = "spring")
public interface ToolMapper {

    @Mappings({
            @Mapping(target = "type", source = "tool.toolType"),
            @Mapping(target = "brand", source = "tool.toolBrand")
    })
    ToolDto mapToDto(Tool tool);


    @Mappings({
            @Mapping(target = "toolType", source = "type"),
            @Mapping(target = "toolBrand", source = "brand"),
            @Mapping(target = "id", source = "toolCreateDto.id"),
            @Mapping(target = "name", source = "toolCreateDto.name")
    })
    Tool mapToModel(ToolCreateDto toolCreateDto, ToolType type, ToolBrand brand);
}

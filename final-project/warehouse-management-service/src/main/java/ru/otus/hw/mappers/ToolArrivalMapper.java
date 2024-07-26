package ru.otus.hw.mappers;

import org.mapstruct.*;
import ru.otus.hw.dto.ToolArrivalDto;
import ru.otus.hw.models.ToolArrivalAct;

@Mapper(componentModel = "spring")
public interface ToolArrivalMapper {
    @Mappings({
            @Mapping(target = "toolDto", source = "tool"),
            @Mapping(target = "toolDto.type", source = "tool.toolType"),
            @Mapping(target = "toolDto.brand", source = "tool.toolBrand")
    })
    ToolArrivalDto mapToDto(ToolArrivalAct toolArrivalAct);
}

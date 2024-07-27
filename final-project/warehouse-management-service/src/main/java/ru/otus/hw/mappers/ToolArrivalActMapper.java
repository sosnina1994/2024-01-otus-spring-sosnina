package ru.otus.hw.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.hw.dto.ToolArrivalActDto;
import ru.otus.hw.models.ToolArrivalAct;

@Mapper(componentModel = "spring")
public interface ToolArrivalActMapper {
    @Mappings({
            @Mapping(target = "toolDto", source = "tool"),
            @Mapping(target = "toolDto.type", source = "tool.toolType"),
            @Mapping(target = "toolDto.brand", source = "tool.toolBrand")
    })
    ToolArrivalActDto mapToDto(ToolArrivalAct toolArrivalAct);
}

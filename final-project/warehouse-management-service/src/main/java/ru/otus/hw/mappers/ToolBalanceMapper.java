package ru.otus.hw.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;
import ru.otus.hw.dto.ToolBalanceDto;
import ru.otus.hw.models.ToolBalance;

@Mapper(componentModel = "spring")
public interface ToolBalanceMapper {

    @Mappings({
            @Mapping(target = "id", source = "toolBalance.tool.id"),
            @Mapping(target = "toolName", source = "toolBalance.tool.name"),
            @Mapping(target = "toolDesignation", source = "toolBalance.tool.designation")
    })
    ToolBalanceDto mapToDto(ToolBalance toolBalance);

}

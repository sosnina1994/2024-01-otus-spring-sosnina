package ru.otus.hw.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.dto.ToolBrandDto;
import ru.otus.hw.models.ToolBrand;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {
    ToolBrandDto mapToDto(ToolBrand manufacturer);

    @Mapping(target = "id", ignore = true)
    ToolBrand mapToModel(ToolBrandDto toolBrandDto);
}

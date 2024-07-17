package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ToolDto {
    private Long id;

    private String name;

    private String designation;

    private ToolTypeDto type;

    private ManufacturerDto manufacturer;

    private Integer count;

    private Integer minBalance;

}


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

    private ToolBrandDto brand;

    private Integer balance;

    private Integer minBalance;

}


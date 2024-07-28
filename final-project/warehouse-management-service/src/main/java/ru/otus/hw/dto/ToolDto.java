package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolDto {
    private Long id;

    private String name;

    private String designation;

    private ToolTypeDto type;

    private ToolBrandDto brand;

}


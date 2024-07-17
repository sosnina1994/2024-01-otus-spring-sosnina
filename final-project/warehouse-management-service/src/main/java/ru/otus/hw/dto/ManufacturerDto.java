package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ManufacturerDto {
    private Long id;

    private String name;
}

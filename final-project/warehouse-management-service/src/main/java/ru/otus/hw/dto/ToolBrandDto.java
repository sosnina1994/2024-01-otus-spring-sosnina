package ru.otus.hw.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ToolBrandDto {
    private Long id;

    @NotBlank(message = "Tool type name can not be null")
    @Size(min = 2, max = 100, message = "Tool type name must be with size from 2 to 100 symbols")
    private String name;
}

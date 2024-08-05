package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolTypeDto {
    private Long id;

    @NotBlank(message = "Tool type name can not be null")
    @Size(min = 2, max = 100, message = "Tool type name must be with size from 2 to 100 symbols")
    private String name;
}

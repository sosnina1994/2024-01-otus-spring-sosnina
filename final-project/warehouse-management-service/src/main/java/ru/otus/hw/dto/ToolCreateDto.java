package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ToolCreateDto {
    private Long id;

    @NotBlank(message = "Tool name can not be null")
    @Size(min = 2, max = 100, message = "Tool name must be with size from 2 to 100 symbols")
    private String name;

    @NotBlank(message = "Tool designation can not be null")
    @Size(min = 2, max = 100, message = "Tool designation must be with size from 2 to 100 symbols")
    private String designation;

    @NotNull(message = "Tool type id must be specified")
    private Long typeId;

    @NotNull(message = "Brand id must be specified")
    private Long brandId;

    @NotNull(message = "Balance must be specified")
    @PositiveOrZero(message = "Balance must be positive")
    private Integer balance;

    @NotNull(message = "Minimal balance must be specified")
    @PositiveOrZero(message = "Minimal balance must be positive")
    private Integer minBalance;

}


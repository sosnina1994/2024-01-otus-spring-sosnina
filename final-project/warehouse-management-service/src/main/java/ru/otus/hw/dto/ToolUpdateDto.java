package ru.otus.hw.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ToolUpdateDto {

    @PositiveOrZero(message = "Balance must be positive")
    private Integer balance;

    @PositiveOrZero(message = "Minimal balance must be positive")
    private Integer minBalance;

}


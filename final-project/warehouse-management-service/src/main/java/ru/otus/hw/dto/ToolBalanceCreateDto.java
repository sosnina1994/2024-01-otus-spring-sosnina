package ru.otus.hw.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolBalanceCreateDto {

    @NotNull(message = "Tool id can not be null")
    private Long toolId;

    @NotNull(message = "Minimal balance can not be null")
    @Positive(message = "Minimal balance can not be less 1")
    private Integer minBalance;
}

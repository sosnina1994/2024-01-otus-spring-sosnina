package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolArrivalCreateDto {

    private Long id;

    @NotBlank(message = "Order number can not be null or empty")
    private String orderNumber;

    @NotNull(message = "Order number can not be null")
    private Long toolId;

    @NotNull(message = "Tool count can not be null")
    @Positive(message = "Tool count must be more 0")
    private Integer count;
}

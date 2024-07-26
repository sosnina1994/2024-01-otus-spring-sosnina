package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
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
public class ToolIssueCreateDto {

    @NotBlank(message = "Rout card number can not be null")
    private String routCardNumber;

    @NotBlank(message = "Product cipher number can not be null")
    private String productCipher;

    @NotBlank(message = "Operation number can not be null")
    private String operationNumber;

    @NotNull(message = "Tool id can not be null")
    private Long toolId;

    @NotNull(message = "Tool count can not be null")
    @Positive(message = "Tool count must be more 0")
    private Integer count;

    @NotBlank(message = "Workplace number can not be null")
    private String workplaceNumber;

    @NotBlank(message = "Employee number can not be null")
    private String employeeName;
}

package ru.otus.hw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolBalanceDto {

    private Long id;

    private String toolName;

    private String toolDesignation;

    private Integer currentBalance;

    private Integer minBalance;
}

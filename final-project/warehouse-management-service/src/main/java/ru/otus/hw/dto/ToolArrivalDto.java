package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolArrivalDto {

    private Long id;

    private String orderNumber;

    private ToolDto toolDto;

    private Integer count;
}

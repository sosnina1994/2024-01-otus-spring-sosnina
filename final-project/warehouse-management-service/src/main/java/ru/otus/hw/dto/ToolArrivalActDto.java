package ru.otus.hw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolArrivalActDto {

    private Long id;

    private String orderNumber;

    private ToolDto toolDto;

    private Integer count;

    private LocalDateTime create;
}

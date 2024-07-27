package ru.otus.hw.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ToolIssueActDto {

    private Long id;

    private ToolDto toolDto;

    private Integer count;

    private String routCardNumber;

    private String productCipher;

    private String operationNumber;

    private String workplaceNumber;

    private String employeeName;

    private LocalDateTime create;

}

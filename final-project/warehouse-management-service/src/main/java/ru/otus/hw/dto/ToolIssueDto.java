package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToolIssueDto {

    private Long id;

    private String routCardNumber;

    private String productCipher;

    private String operationNumber;

    private ToolDto toolDto;

    private Integer count;

    private String workplaceNumber;

    private String employeeName;
}

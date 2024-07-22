package ru.otus.hw.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ToolIssueDto {

    private Long id;

    private String routCardNumber;

    private String productCipher;

    private String operationNumber;

    private List<ToolDto> tools;

    private LocalDate date;

    private String workplaceNumber;

    private String employeeName;
}

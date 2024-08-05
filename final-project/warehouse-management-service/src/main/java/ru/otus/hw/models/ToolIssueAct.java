package ru.otus.hw.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue_reports")
public class ToolIssueAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_id")
    private Tool tool;

    @Column(name = "count")
    private Integer count;

    @Column(name = "route_card_number")
    private String routCardNumber;

    @Column(name = "product_cipher")
    private String productCipher;

    @Column(name = "operation_number")
    private String operationNumber;

    @Column(name = "workplace_number")
    private String workplaceNumber;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "create_at")
    private LocalDateTime create;

}

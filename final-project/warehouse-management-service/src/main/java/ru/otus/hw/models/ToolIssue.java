package ru.otus.hw.models;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issues")
public class ToolIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_card_number")
    private String routCardNumber;

    @Column(name = "product_cipher")
    private String productCipher;

    @Column(name = "operation_number")
    private String operationNumber;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Tool.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "tool_issues",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "tool_id"))
    private List<Tool> tools;

    @Column(name = "issue_date")
    private LocalDate date;

    @Column(name = "workplace_number")
    private String workplaceNumber;

    @Column(name = "employee_name")
    private String employeeName;
}

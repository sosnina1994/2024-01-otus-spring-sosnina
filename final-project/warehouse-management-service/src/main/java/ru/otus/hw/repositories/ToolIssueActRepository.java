package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.ToolIssueAct;

import java.util.List;

public interface ToolIssueActRepository extends JpaRepository<ToolIssueAct, Long> {

    List<ToolIssueAct> findAll();
}

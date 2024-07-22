package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.ToolIssue;


public interface ToolIssueRepository extends JpaRepository<ToolIssue, Long> {

}

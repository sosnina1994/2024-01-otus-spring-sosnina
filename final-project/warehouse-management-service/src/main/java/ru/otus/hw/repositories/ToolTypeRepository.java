package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.ToolType;

public interface ToolTypeRepository extends JpaRepository<ToolType, Long> {
}

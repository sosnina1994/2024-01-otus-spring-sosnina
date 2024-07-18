package ru.otus.hw.repositories;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import ru.otus.hw.models.Tool;

import java.util.List;
import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    @Override
    @EntityGraph("tool-graph")
    List<Tool> findAll();

    @Override
    @EntityGraph("tool-graph")
    Optional<Tool> findById(@Nonnull Long id);

}

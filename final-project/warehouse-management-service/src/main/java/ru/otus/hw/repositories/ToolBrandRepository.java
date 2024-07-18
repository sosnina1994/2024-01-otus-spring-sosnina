package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.ToolBrand;

public interface ToolBrandRepository extends JpaRepository<ToolBrand, Long> {
}

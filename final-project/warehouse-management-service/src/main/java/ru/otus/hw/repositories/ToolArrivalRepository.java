package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.ToolArrivalAct;

public interface ToolArrivalRepository extends JpaRepository<ToolArrivalAct, Long> {
}

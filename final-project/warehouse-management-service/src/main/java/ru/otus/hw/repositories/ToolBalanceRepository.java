package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.ToolBalance;

import java.util.Optional;

public interface ToolBalanceRepository extends JpaRepository<ToolBalance, Long> {
    Optional<ToolBalance> findToolBalancesByToolId(Long id);
}

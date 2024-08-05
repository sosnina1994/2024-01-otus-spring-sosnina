package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.models.ToolBalance;

import java.util.Optional;
import java.util.List;

public interface ToolBalanceRepository extends JpaRepository<ToolBalance, Long> {
    Optional<ToolBalance> findToolBalancesByToolId(Long id);

    @Query(
            nativeQuery = true,
            value = "select * from tool_balances tb where tb.current_balance < tb.min_balance"
    )
    List<ToolBalance> findMissingToolBalance();
}

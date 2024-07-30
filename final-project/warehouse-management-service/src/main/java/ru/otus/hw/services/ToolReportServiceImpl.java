package ru.otus.hw.services;

import lombok.val;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.helpers.BalanceReportExcelGeneratorHelper;
import ru.otus.hw.repositories.ToolBalanceRepository;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class ToolReportServiceImpl implements ToolReportService {
    private final ToolBalanceRepository toolBalanceRepository;

    @Override
    @Transactional(readOnly = true)
    public ByteArrayInputStream getCurrentToolBalance() {
        val data = toolBalanceRepository.findAll();
        return BalanceReportExcelGeneratorHelper.balanceToExel(data);
    }

    @Override
    @Transactional(readOnly = true)
    public ByteArrayInputStream getMissingToolBalance() {
        val data = toolBalanceRepository.findMissingToolBalance();
        return BalanceReportExcelGeneratorHelper.balanceToExel(data);
    }
}

package ru.otus.hw.services;

import java.io.ByteArrayInputStream;

public interface ToolReportService {
    ByteArrayInputStream getCurrentToolBalance();

    ByteArrayInputStream getMissingToolBalance();
}

package ru.otus.hw.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw.services.ToolReportService;

@Controller
@RequiredArgsConstructor
@Tag(name = "ToolReportController", description = "АПИ для загрузки отчетов в формате xlsx")
public class ToolReportController {

    private final ToolReportService toolReportService;

    @Operation(description = "Отчет по остаткам инструмента")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reports/balances/load")
    public ResponseEntity<Resource> currentBalanceLoad() {
        String filename = "current_balance.xlsx";
        InputStreamResource file = new InputStreamResource(toolReportService.getCurrentToolBalance());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    @Operation(description = "Отчет по недостающим остаткам инструмента")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reports/missing-balances/load")
    public ResponseEntity<Resource> missingBalanceLoad() {
        String filename = "missing_balance.xlsx";
        InputStreamResource file = new InputStreamResource(toolReportService.getMissingToolBalance());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

}

package ru.otus.hw.helpers;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.otus.hw.exceptions.FileGenerationException;
import ru.otus.hw.models.ToolBalance;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Slf4j
public class BalanceReportExcelGeneratorHelper {
    private static final String[] HEADERS = { "Id", "Name", "Designation", "Type",
            "Brand", "Current balance", "Min balance" };

    private static final String SHEET = "Tools";

    public static ByteArrayInputStream balanceToExel(List<ToolBalance> balances) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }
            createRowCell(sheet, balances);
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            log.warn("Fail to import data to Excel file: {}", e.getMessage());
            throw new FileGenerationException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    private static void createRowCell(Sheet sheet, List<ToolBalance> balances) {
        int rowIdx = 1;
        for (ToolBalance balance : balances) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(balance.getId());
            row.createCell(1).setCellValue(balance.getTool().getName());
            row.createCell(2).setCellValue(balance.getTool().getDesignation());
            row.createCell(3).setCellValue(balance.getTool().getToolType().getName());
            row.createCell(4).setCellValue(balance.getTool().getToolBrand().getName());
            row.createCell(5).setCellValue(balance.getCurrentBalance());
            row.createCell(6).setCellValue(balance.getMinBalance());
        }
    }
}

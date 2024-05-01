package com.evy.framework.data;

import com.evy.framework.utils.CustomException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provides data from an Excel file located at 'src/main/resources/Book1.xlsx' for test data-driven testing.
 */
public final class ExcelDataProvider {

    // Path to the Excel file
    private final static Path filePath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "Book1.xlsx");

    // Private constructor to prevent instantiation
    private ExcelDataProvider() {
    }


    /**
     * Retrieves data from the specified sheet in the Excel file.
     *
     * @param sheetName Name of the sheet in the Excel file.
     * @return 2D array of data from the specified sheet.
     * @throws CustomException if there is an error reading the Excel file.
     */
    public static Object[][] getData(String sheetName) {
        try (FileInputStream fis = new FileInputStream(String.valueOf(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            int numRows = sheet.getPhysicalNumberOfRows();
            int numCols = sheet.getRow(0).getPhysicalNumberOfCells();
            Object[][] data = new Object[numRows - 1][numCols];

            for (int i = 1; i < numRows; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < numCols; j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        data[i - 1][j] = cell.toString();
                    }
                }
            }
            return data;
        } catch (IOException e) {
            System.err.println("Error reading Excel file. Exception details: " + e.getMessage());
            throw new CustomException("Error reading Excel file", e);
        }
    }
}

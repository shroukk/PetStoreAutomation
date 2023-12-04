package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XLUtility {
    public static Object[][] getCellsData(String filePath, String sheetName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheet(sheetName);
        DataFormatter dataFormatter = new DataFormatter();

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                data[i - 1][j] = dataFormatter.formatCellValue(cell).toString();
            }
        }
        workbook.close();
        fileInputStream.close();
        return data;
    }

    public static String[] getUsernamesCells(String filePath, String sheetName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheet(sheetName);
        DataFormatter dataFormatter = new DataFormatter();

        int rowCount = sheet.getPhysicalNumberOfRows();
        String [] data = new String[rowCount-1];
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            data[i - 1]= dataFormatter.formatCellValue(cell);
        }
        workbook.close();
        fileInputStream.close();
        return data;
    }
}

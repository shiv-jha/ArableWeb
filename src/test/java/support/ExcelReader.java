package support;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    static String projectDir = System.getProperty("user.dir");

    public static Map<String, Map<String, String>> getExcelDataAsMap(String path, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int lastRow = sheet.getLastRowNum();
        Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();
        Map<String, String> dataMap = new HashMap<String, String>();
        //Looping over entire row
        for (int i = 0; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            //1st Cell as Value
            Cell valueCell = row.getCell(1);
            //0th Cell as Key
            Cell keyCell = row.getCell(0);
            String value = valueCell.getStringCellValue().trim();
            String key = keyCell.getStringCellValue().trim();
            //Putting key & value in dataMap
            dataMap.put(key, value);
            //Putting dataMap to excelFileMap
            excelFileMap.put("DataSheet", dataMap);
        }
        //Returning excelFileMap
        return excelFileMap;
    }

    public static void setValueForColumn(String pageName, String cellValue, String roleName) {
        String userDirector = System.getProperty("user.dir");
        try {
            File src = new File(userDirector + "\\data\\excelSheet.xlsx");
            XSSFWorkbook wb;
            XSSFSheet sheet;
            if (!src.exists()) {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("Web");
                XSSFRow rowHead = sheet.createRow((short) 0);
                rowHead.createCell(0).setCellValue("Date");
                rowHead.createCell(1).setCellValue("Page");
                rowHead.createCell(2).setCellValue("member");
                rowHead.createCell(3).setCellValue("orgAdmin");
                rowHead.createCell(4).setCellValue("orgReader");
                rowHead.createCell(5).setCellValue("sysReader");
                rowHead.createCell(6).setCellValue("teamAdmin");
                FileOutputStream fileOut = new FileOutputStream(src);
                System.out.println("Excel File has been created successfully.");
                wb.write(fileOut);
                fileOut.close();
            }
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet("Web");
            int rowCount = findRow(sheet, pageName);
            XSSFRow row = sheet.getRow(rowCount);
            int columnCount = findColumn(sheet, roleName);
            row.createCell(columnCount).setCellValue(cellValue);
            FileOutputStream fileOut = new FileOutputStream(src);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
        }
    }

    private static int findRow(XSSFSheet sheet, String value1) {
        //to find row number so we can search through that specific column
        int sheetRow = 0;
        outerloop:
        for (Row row : sheet) {
            for (Cell cell : row) {
                String value = readCellData(cell);
                if (value.equals(value1)) {
                    sheetRow = row.getRowNum();
                    break outerloop;
                }
            }
        }
        return sheetRow;
    }

    private static int findColumn(XSSFSheet sheet, String roleName) {
        //to find row number so we can search through that specific column
        int i;
        outerloop:
        for (i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(i);

            if (cell.getStringCellValue().equalsIgnoreCase(roleName)) {
                break outerloop;
            }
        }
        return i;
    }

    public static String readCellData(Cell cell) {
        String value = null;

        if (cell != null) {
            CellType type = cell.getCellType();

            if (type == CellType.STRING) {
                value = cell.getStringCellValue();
            } else if (type == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = cell.getDateCellValue();
                    value = dateFormat.format(date);
                } else {
                    value = String.valueOf(cell.getStringCellValue());
                }
            } else if (type == CellType.BOOLEAN) {
                value = String.valueOf(cell.getBooleanCellValue());
            }
        } else {
            value = " ";
        }
        return value;
    }

    public String getValueForColumn(String fileName, String sheetName, int rowNum, String columnName) {
        String userDirector = System.getProperty("user.dir");
        try {
            File src = new File(userDirector + "/Resource/" + fileName);
            FileInputStream fis = new FileInputStream(src);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh1 = wb.getSheet(sheetName);
            int i = 0;
            for (i = 0; i < sh1.getRow(0).getLastCellNum(); i++) {
                if (sh1.getRow(0).getCell(i).getStringCellValue().contentEquals(columnName)) {
                    break;
                }
            }
            return sh1.getRow(rowNum).getCell(i).getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public String[][] getExcelData(String fileName, String sheetName) {
        String[][] arrayExcelData = null;
        org.apache.poi.ss.usermodel.Workbook tempWB;
        try {
            String path = projectDir + "/Resource/TestData/" + fileName + ".xlsx";
            System.out.println(path);
            tempWB = new XSSFWorkbook(projectDir + "/Resource/TestData/" + fileName + ".xlsx");
            org.apache.poi.ss.usermodel.Sheet sheet = tempWB.getSheet(sheetName);
            // Total rows counts the top heading row
            int totalNoOfRows = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int totalNoOfCols = row.getLastCellNum();
            arrayExcelData = new String[totalNoOfRows][totalNoOfCols];
            try {
                for (int i = 1; i < totalNoOfRows + 1; i++) {
                    for (int j = 0; j < totalNoOfCols; j++) {
                        try {
                            arrayExcelData[i - 1][j] = sheet.getRow(i).getCell(j).toString().trim();
                        } catch (Exception e) {
                            arrayExcelData[i - 1][j] = "";
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        return arrayExcelData;
    }
}


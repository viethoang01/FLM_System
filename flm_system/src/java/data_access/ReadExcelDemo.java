package data_access;

import entity.Book;
import entity.Subject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelDemo {

    public static final int COLUMN_INDEX_CODE = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_SEMESTER = 2;
    public static final int COLUMN_INDEX_NO_CREDIT = 3;
//    public static final int COLUMN_INDEX_TOTAL = 4;

    public static void main(String[] args) throws IOException {
        final String excelFilePath = "template-import-subjects.xlsx";
//         String err = readExcel(excelFilePath,null);
        List<Subject> subjects = new ArrayList<>();;
        String err = readExcel(excelFilePath, subjects);
        if (err == null) {
            for (Subject subject : subjects) {
                System.out.println(subject.toString()
                );
            }
        }

        System.out.println(err);
    }

    public static String readExcel(String excelFilePath, List<Subject> subjects) {
        boolean check = false;
        String err = "";
        try {

// Get workbook
            try ( // Get file
                     InputStream inputStream = new FileInputStream(new File(excelFilePath))) {
                // Get workbook
                Workbook workbook = getWorkbook(inputStream, excelFilePath);

// Get sheet
                Sheet sheet = workbook.getSheetAt(0);

// Get all rows
                Iterator<Row> iterator = sheet.iterator();
                while (iterator.hasNext()) {
                    Row nextRow = iterator.next();
                    if (nextRow.getRowNum() == 0) {
                        // Ignore header
                        continue;
                    }

                    // Get all cells
                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    // Read cells and set value for book object
                    Subject subject = new Subject();
                    while (cellIterator.hasNext()) {
                        //Read cell
                        Cell cell = cellIterator.next();
                        Object cellValue = getCellValue(cell);
//                        if (cellValue.toString().equals("") || cellValue.toString().isEmpty()) {
//                            err = "Column: " + (cell.getColumnIndex() + 1) + ",row: " + (1 + (cell.getRow().getRowNum())) + " is blank \n" + err;
////                            System.out.println(" :" + err);
//                            check = true;
//                        }

                        // Set value for book object
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) {
                            case COLUMN_INDEX_CODE:
                                if (cellValue.toString().equals("") || cellValue.toString() == null) {
                                    err = "Column: " + (cell.getColumnIndex() + 1) + ",roww: " + (1 + (cell.getRow().getRowNum())) + " is blank \n" + err;
                                   
                                    System.out.println(err);
                                } else {
                                    System.out.println(cellValue);
                                    subject.setCode((String) getCellValue(cell));
                                    check = false;
                                }
                                subject.setCode((String) getCellValue(cell));

                                break;
                            case COLUMN_INDEX_NAME:
                                subject.setName((String) getCellValue(cell));
                                break;
//
                            case COLUMN_INDEX_SEMESTER:
                try {
                                subject.setSemester((int) (new BigDecimal((double) cellValue).intValue()));
                            } catch (Exception w) {
                                err = "Column: " + (cell.getColumnIndex() + 1) + ",row: " + (1 + (cell.getRow().getRowNum())) + " must is number \n" + err;
                                check = true;
//                                System.out.println(COLUMN_INDEX_SEMESTER + ": " + err);
                            }
                            break;
                            case COLUMN_INDEX_NO_CREDIT:
                                try {
                                subject.setSemester((int) (new BigDecimal((double) cellValue).intValue()));
                            } catch (Exception w) {
                                err = "Column: " + (cell.getColumnIndex() + 1) + ",row: " + (1 + (cell.getRow().getRowNum())) + " must is number \n" + err;
                                check = true;
//                                System.out.println(COLUMN_INDEX_SEMESTER + ": " + err);
                            }
                            break;

                            default:
                                break;
                        }

                    }
                    subjects.add(subject);

                }

                workbook.close();
            }

            if (!check) {
                err = null;
            }
        } catch (IOException ex) {
//            return err;
//            Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            return err;
        }

    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
                cellValue = "";
                break;

            case ERROR:
                cellValue = null;
                break;
            default:
                break;
        }

        return cellValue;
    }
}

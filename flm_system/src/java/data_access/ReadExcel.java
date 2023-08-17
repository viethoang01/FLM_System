package data_access;

import static data_access.ReadExcelDemo.COLUMN_INDEX_NO_CREDIT;
import static data_access.ReadExcelDemo.COLUMN_INDEX_SEMESTER;
import entity.Book;
import entity.Plo;
import entity.Po;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    public static final int COLUMN_INDEX_CODE = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_SEMESTER = 2;
    public static final int COLUMN_INDEX_NO_CREDIT = 3;
//    public static final int COLUMN_INDEX_TOTAL = 4;

    public static void main(String[] args) throws IOException {
        final String excelFilePath = "subjects.xlsx";
//        final List<Subject> subjects = readExcel(excelFilePath);
//        for (Subject subject : subjects) {
//            System.out.println(subject.toString()
//            );
//        }
    }

    public static String readExcelwithSubject(InputStream inputStream, String excelFilePath, List<Subject> subjects, int curiculum_id) {
        StringBuilder err = new StringBuilder();
        try {
            try ( Workbook workbook = getWorkbook(inputStream, excelFilePath)) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row nextRow : sheet) {
                    if (nextRow.getRowNum() == 0) {
                        continue;
                    }

                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    // Read cells and set value for book object
                    Subject subject = new Subject();
                    boolean valid = true;
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        Object cellValue = getCellValue(cell);
                        if (cellValue == null || cellValue.toString().equals("") || cellValue.toString().isEmpty()) {
                            err.append("Row: " + (1 + (cell.getRow().getRowNum())) + " - Column: " + (cell.getColumnIndex() + 1) + " is blank <br>");
                            valid = false;
                            continue;
                        }

                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) {
                            case COLUMN_INDEX_CODE:
                                subject.setCode((String) getCellValue(cell));
                                if (new SubjectDAO().checkSubjectExist(subject.getCode(), curiculum_id)) {
                                    err.append("Row: " + (1 + (cell.getRow().getRowNum())) + " - Column: " + (cell.getColumnIndex() + 1) + " is duplicate <br>");
                                    valid = false;
                                }
                                break;
                            case COLUMN_INDEX_NAME:
                                subject.setName((String) getCellValue(cell));
                                break;
                            default:
                                break;
                        }
                    }
                    if (valid) {
                        subjects.add(subject);
                    }

                }
            }
            inputStream.close();
        } catch (IOException ex) {
        } finally {
            return err.toString();
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
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

    public static String readExcelwithPO(InputStream inputStream, String excelFilePath, List<Po> pos, int curiculum_id) {
        StringBuilder err = new StringBuilder();
        try {
            try ( Workbook workbook = getWorkbook(inputStream, excelFilePath)) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row nextRow : sheet) {
                    if (nextRow.getRowNum() == 0) {
                        continue;
                    }

                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    // Read cells and set value for book object
                    Po po = new Po();
                    boolean valid = true;
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        Object cellValue = getCellValue(cell);
                        if (cellValue == null || cellValue.toString().equals("") || cellValue.toString().isEmpty()) {
                            err.append("Row: " + (1 + (cell.getRow().getRowNum())) + " - Column: " + (cell.getColumnIndex() + 1) + " is blank <br>");
                            valid = false;
                            continue;
                        }

                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) {
                            case COLUMN_INDEX_CODE:
                                po.setName((String) getCellValue(cell));
                                if (new PoDAO().checkPoExist(po.getName(), curiculum_id)) {
                                    err.append("Row: " + (1 + (cell.getRow().getRowNum())) + " - Column: " + (cell.getColumnIndex() + 1) + " is duplicate <br>");
                                    valid = false;
                                }
                                break;
                            case COLUMN_INDEX_NAME:
                                po.setDescription((String) getCellValue(cell));
                                break;
                            default:
                                break;
                        }
                    }
                    if (valid) {
                        po.setCurriculum_id(curiculum_id);
                        pos.add(po);
                    }

                }
            }
            inputStream.close();
        } catch (IOException ex) {
        } finally {
            return err.toString();
        }
    }

    public static String readExcelwithPLO(InputStream inputStream, String excelFilePath, List<Plo> plos, int curiculum_id) {
        StringBuilder err = new StringBuilder();
        try {
            try ( Workbook workbook = getWorkbook(inputStream, excelFilePath)) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row nextRow : sheet) {
                    if (nextRow.getRowNum() == 0) {
                        continue;
                    }

                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    // Read cells and set value for book object
                    Plo plo = new Plo();
                    boolean valid = true;
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        Object cellValue = getCellValue(cell);
                        if (cellValue == null || cellValue.toString().equals("") || cellValue.toString().isEmpty()) {
                            err.append("Row: " + (1 + (cell.getRow().getRowNum())) + " - Column: " + (cell.getColumnIndex() + 1) + " is blank <br>");
                            valid = false;
                            continue;
                        }

                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) {
                            case COLUMN_INDEX_CODE:
                                plo.setName((String) getCellValue(cell));
                                if (new PloDAO().checkPloExist(plo.getName(), curiculum_id)) {
                                    err.append("Row: " + (1 + (cell.getRow().getRowNum())) + " - Column: " + (cell.getColumnIndex() + 1) + " is duplicate <br>");
                                    valid = false;
                                }
                                break;
                            case COLUMN_INDEX_NAME:
                                plo.setDescription((String) getCellValue(cell));
                                break;
                            default:
                                break;
                        }
                    }
                    if (valid) {
                        plo.setCurriculum_id(curiculum_id);
                        plos.add(plo);
                    }

                }
            }
            inputStream.close();
        } catch (IOException ex) {
        } finally {
            return err.toString();
        }
    }

}

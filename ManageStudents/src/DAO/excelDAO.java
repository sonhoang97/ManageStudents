package DAO;

import ModelEntity.*;

import ModelEntity.Class;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class excelDAO {
    private StudentDAO studentDAO;
    private ClassDAO classDAO;
    private SubjectDAO subjectDAO;
    public excelDAO() {
        studentDAO = new StudentDAO();
        classDAO = new ClassDAO();
        subjectDAO = new SubjectDAO();
    }

    public List<Student> getStudentsExcel() throws IOException {
        List<Student> students = new ArrayList<Student>();
        ClassDAO clsDAO = new ClassDAO();
        String filePath = new File("").getAbsolutePath();
        String path = "/src/Data/managestudents.xlsx";
        InputStream inputStream = new FileInputStream(new File(filePath+path));
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Student std = new Student();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        std.setName((String) getCellValue(cell));
                        break;
                    case 1:
                        std.setMssv(((Double) cellValue).intValue());
                        break;
                    case 2:
                        std.setSex((String) getCellValue(cell));
                        break;
                    case 3:
                        std.setCmnd(((Double) cellValue).intValue());
                        break;
                    case 4:
                        String classId = (String) getCellValue(cell);
                        std.setClassStd(clsDAO.getClass(classId));
                        break;
                    case 5:
                        Object a = getCellValue(cell);
                        std.setPassword(String.valueOf(a));
                        break;
                    default:
                        break;
                }

            }
            students.add(std);
        }
        workbook.close();
        inputStream.close();
        return students;
    }

    //
    public List<Schedule> getSchedulesExcel() throws IOException {
        List<Schedule> Schedules = new ArrayList<Schedule>();
        String filePath = new File("").getAbsolutePath();
        String path = "/src/Data/managestudents.xlsx";
        InputStream inputStream = new FileInputStream(new File(filePath+path));

        ClassDAO clsDAO = new ClassDAO();
        SubjectDAO sbjDAO = new SubjectDAO();

        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(3);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Schedule schedule = new Schedule();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        schedule.setClassSch(clsDAO.getClass((String) getCellValue(cell)));
                        break;
                    case 1:
                        schedule.setSubjectSch(sbjDAO.getSubjectDB((String) getCellValue(cell)));
                        break;
                    case 2:
                        schedule.setRoom((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            Schedules.add(schedule);
        }
        workbook.close();
        inputStream.close();
        return Schedules;
    }

    public List<Class> getClassesExcel() throws IOException {
        String filePath = new File("").getAbsolutePath();
        String path = "/src/Data/managestudents.xlsx";
        InputStream inputStream = new FileInputStream(new File(filePath+path));

        List<Class> Classes = new ArrayList<Class>();
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(1);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Class cls = new Class();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        cls.setId((String) getCellValue(cell));
                        break;
                    case 1:
                        cls.setClassName((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            Classes.add(cls);
        }
        workbook.close();
        inputStream.close();
        return Classes;
    }

    public List<Subject> getSubjectsExcel() throws IOException {
        String filePath = new File("").getAbsolutePath();
        String path = "/src/Data/managestudents.xlsx";
        InputStream inputStream = new FileInputStream(new File(filePath+path));
        List<Subject> Subjects = new ArrayList<Subject>();
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(2);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Subject sbj = new Subject();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        sbj.setId((String) getCellValue(cell));
                        break;
                    case 1:
                        sbj.setSubjectName((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            Subjects.add(sbj);
        }
        workbook.close();
        inputStream.close();
        return Subjects;
    }

    public List<Transcript> getTranscriptExcel() throws IOException {

        String filePath = new File("").getAbsolutePath();
        String path = "/src/Data/managestudents.xlsx";
        InputStream inputStream = new FileInputStream(new File(filePath+path));

        List<Transcript> trans = new ArrayList<Transcript>();
        Workbook workbook = getWorkbook(inputStream, path);
        Sheet sheetClass = workbook.getSheetAt(4);

        Iterator<Row> iterator = sheetClass.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Transcript tran = new Transcript();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        int mssv = ((Double) cellValue).intValue();
                        Student std = studentDAO.getStudent(mssv);
                        tran.setStudentTrans(std);
                        break;
                    case 1:
                        tran.setMidTerm((Double) getCellValue(cell));
                        break;
                    case 2:
                        tran.setEndTerm((Double) getCellValue(cell));
                        break;
                    case 3:
                        tran.setBonus((Double) getCellValue(cell));
                        break;
                    case 4:
                        tran.setPoint((Double) getCellValue(cell));
                        break;
                    case 5:
                        String subjectId = (String) getCellValue(cell);
                        Subject sbj = subjectDAO.getSubjectDB(subjectId);
                        tran.setSubjectTrans(sbj);
                        break;
                    case 6:
                        String classId = (String) getCellValue(cell);
                        Class cls = classDAO.getClass(classId);
                        tran.setClassTrans(cls);
                        break;


                    default:
                        break;
                }

            }
            trans.add(tran);
        }
        workbook.close();
        inputStream.close();
        return trans;
    }

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

    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
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
}

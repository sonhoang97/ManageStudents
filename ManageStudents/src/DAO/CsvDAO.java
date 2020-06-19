package DAO;

import Model.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvDAO {
    public CsvDAO() {
    }

    public List<Student> getListStudentCsv() throws IOException {
        List<Student> students = new ArrayList<Student>();
        String path = "F:/java/Project/github/ManageStudents/ManageStudents//src/DataCSV/students.csv";
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        CSVParser csvParser = new CSVParser(reader,CSVFormat.EXCEL);
        for (CSVRecord csvRecord : csvParser) {
            Student std = new Student();
            std.setMssv(Integer.parseInt(csvRecord.get(1)));
            std.setName(csvRecord.get(0));
            std.setSex(csvRecord.get(2));
            std.setCmnd(Integer.parseInt(csvRecord.get(3)));
            students.add(std);
        }
        return students;
    }
}

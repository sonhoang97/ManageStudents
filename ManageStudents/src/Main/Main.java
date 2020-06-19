package Main;

import DAO.CsvDAO;
import DAO.StudentDAO;
import Model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentDAO studentDAO = new StudentDAO();
//        List<Student> ds= new ArrayList<Student>();
//        ds= studentDAO.getListStudent();
//
        //Student std = studentDAO.getStudent(1512462);
        CsvDAO csvDAO = new CsvDAO();
        List<Student> ds = csvDAO.getListStudentCsv();
        for(int i=0; i<ds.size(); i++){
            Student sv=ds.get(i);
            studentDAO.addStudent(sv);
        }
    }
}

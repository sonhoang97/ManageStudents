package Controller;

import DAO.ClassDAO;
import DAO.ScheduleDAO;
import DAO.StudentDAO;
import DAO.excelDAO;

import ModelEntity.Class;
import ModelEntity.Schedule;
import ModelEntity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiaoVuController {
    private excelDAO excelDAO;
    private StudentDAO studentDAO;
    private ClassDAO classDAO;
    private ScheduleDAO scheduleDAO;

    public GiaoVuController() {
        this.excelDAO = new excelDAO();
        this.classDAO = new ClassDAO();
        this.scheduleDAO = new ScheduleDAO();
        this.studentDAO = new StudentDAO();
    }

    public boolean addClasses() throws IOException {
        try {
            List<Class> classes = new ArrayList<Class>();
            classes = excelDAO.getClassesExcel();
            for (Class cls : classes) {
                classDAO.AddClassDB(cls);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addStudents() throws IOException {
        try {
            List<Student> students = new ArrayList<Student>();
            students = excelDAO.getStudentsExcel();
            for (Student std : students) {
                studentDAO.addStudent(std);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addStudent(Student std) {
        return studentDAO.addStudent(std);
    }

    public boolean addSchedules() throws IOException {
        try {
            List<Schedule> schedules = new ArrayList<Schedule>();
            schedules = excelDAO.getSchedulesExcel();

            for (Schedule sch : schedules) {
                scheduleDAO.addSchedule(sch);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public List<Student> getAllStudents(){
        return studentDAO.getStudents();
    }
    // Todo
    public boolean getStudentsByClass(String classId) {
        return true;
    }

    // Todo

    public boolean getSudentsBySubjectClass(String subjectId, String classId) {
        return true;
    }

    public List<Schedule> getSchedules() {
        try {
            return scheduleDAO.getSchedules();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Schedule>();
        }
    }

    // Todo
    public List<Schedule> getSchedulesByClass(String classId){
        return null;
    }


}

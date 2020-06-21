package Controller;

import DAO.*;

import ModelEntity.*;
import ModelEntity.Class;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiaoVuController {
    private excelDAO excelDAO;
    private StudentDAO studentDAO;
    private ClassDAO classDAO;
    private ScheduleDAO scheduleDAO;
    private SubjectDAO subjectDAO;
    private TranscriptDAO transcriptDAO;
    public GiaoVuController() {
        this.excelDAO = new excelDAO();
        this.classDAO = new ClassDAO();
        this.scheduleDAO = new ScheduleDAO();
        this.studentDAO = new StudentDAO();
        this.subjectDAO = new SubjectDAO();
        this.transcriptDAO = new TranscriptDAO();
    }
    public String[] getClassesId(){
        List<Class> clses = classDAO.getAllClasses();
        String[] strs = new String[clses.size()];
        int i = 0;
        for(Class cls: clses){
            strs[i++] = cls.getId();
        }
        return strs;
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

    public List<Student> getStudentsByClass(String classId) {
        List<Student> stds = new ArrayList<Student>();
        stds =  new ArrayList<>(classDAO.getClass(classId).getStudents());
        return stds;
    }

    public Class getClass(String classId){
        return classDAO.getClass(classId);
    }

    //todo
    public List<Student> getSudentsBySubjectClass(String subjectId, String classId) {
        return new ArrayList<>();
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
        return scheduleDAO.getSchedulesByClass(classId);
    }

    public List<Subject> getSubjectsByClass(String classId){return subjectDAO.getSubjectsByClass(classId);}

    public List<Transcript> getTranscripts(){
        return transcriptDAO.getTranscripts();
    }

    public boolean updateTranscript(Transcript tran){return transcriptDAO.updateTranscripts(tran);}
}

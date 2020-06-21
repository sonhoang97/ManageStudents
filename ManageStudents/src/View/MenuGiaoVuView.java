package View;

import DAO.*;
import ModelEntity.Schedule;
import ModelEntity.Student;
import ModelEntity.Subject;
import ModelEntity.Transcript;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuGiaoVuView extends JFrame{
    private JButton addClassesBtn;
    private JButton addScheduleBtn;
    private JButton getClassesBtn;
    private JButton getSchedules;
    private JButton addTranscriptsBtn;
    private JButton getTranscriptBtn;
    private JButton logOutBtn;
    private SpringLayout layout;

    private excelDAO excelDAO;
    private StudentDAO studentDAO;
    private ClassDAO classDAO;
    private SubjectDAO subjectDAO;
    private ScheduleDAO scheduleDAO;
    private TranscriptDAO transcriptDAO;
    public MenuGiaoVuView() {
        addClassesBtn = new JButton();
        addScheduleBtn = new JButton();
        getClassesBtn = new JButton();
        getSchedules = new JButton();
        addTranscriptsBtn = new JButton();
        getTranscriptBtn = new JButton();
        logOutBtn = new JButton();

        excelDAO = new excelDAO();
        studentDAO = new StudentDAO();
        classDAO = new ClassDAO();
        subjectDAO = new SubjectDAO();
        scheduleDAO = new ScheduleDAO();
        transcriptDAO = new TranscriptDAO();

        excute();
    }

    public void excute(){
        setText();
        addClassesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> stds = new ArrayList<>();
                try {
                    stds = excelDAO.getStudentsExcel();
                    for (Student std: stds){
                        studentDAO.addStudent(std);
                    }
                    showMessage("Import thành công!");
                } catch (IOException ioException) {
                    showMessage("Import thất bại!");
                    ioException.printStackTrace();
                }
            }
        });
        addScheduleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Schedule> schs = new ArrayList<>();
                try {
                    schs = excelDAO.getSchedulesExcel();
                    for (Schedule sch: schs){
                        scheduleDAO.addSchedule(sch);
                    }
                    showMessage("Import thành công!");
                } catch (IOException ioException) {
                    showMessage("Import thất bại!");
                    ioException.printStackTrace();
                }
            }
        });
        getClassesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassesStdView classesStdView = new ClassesStdView();
                classesStdView.showClassStdView();
                setVisible(false);
            }
        });
        getSchedules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleView scheduleView = new ScheduleView();
                scheduleView.showViewSchedule();
                setVisible(false);
            }
        });
        addTranscriptsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Transcript> trans = new ArrayList<>();
                try {
                    trans = excelDAO.getTranscriptExcel();
                    for (Transcript tran: trans){
                        transcriptDAO.addTranscript(tran);
                    }
                    showMessage("Import thành công!");
                } catch (IOException ioException) {
                    showMessage("Import thất bại!");
                    ioException.printStackTrace();
                }
            }
        });
        getTranscriptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TranscriptView transcriptView = new TranscriptView();
                transcriptView.showViewTranscript();
                setVisible(false);
            }
        });

        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView login = new LoginView();
                login.showDialog();
                setVisible(false);
            }
        });

        this.add(addClassesBtn);
        this.add(addScheduleBtn);
        this.add(getClassesBtn);
        this.add(getSchedules);
        this.add(addTranscriptsBtn);
        this.add(getTranscriptBtn);
        this.add(logOutBtn);
        this.layout = new SpringLayout();
        this.setLayout(layout);

        layout.putConstraint(SpringLayout.NORTH, addClassesBtn, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, addClassesBtn, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, addScheduleBtn, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, addScheduleBtn, 200, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, getClassesBtn, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, getClassesBtn, 400, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, getSchedules, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, getSchedules, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, addTranscriptsBtn, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, addTranscriptsBtn, 200, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, getTranscriptBtn, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, getTranscriptBtn, 400, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, logOutBtn, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, logOutBtn, 200, SpringLayout.NORTH, this);

        this.setSize(600, 250);
        this.setVisible(true);
    }
    public void showViewGiaoVu() {
        setVisible(true);
    }
    private void setText(){
        addClassesBtn.setText("Import danh sách lớp");
        addScheduleBtn.setText("Import thời khoá biểu");
        getSchedules.setText("Xem thời khoá biểu");
        getClassesBtn.setText("Xem danh sách lớp");
        addTranscriptsBtn.setText("Import bảng điểm");
        getTranscriptBtn.setText("Xem bảng điểm");
        logOutBtn.setText("Đăng xuất");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

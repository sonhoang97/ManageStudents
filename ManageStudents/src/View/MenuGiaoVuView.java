package View;

import DAO.excelDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGiaoVuView extends JFrame{
    private JButton addClassesBtn;
    private JButton addScheduleBtn;
    private JButton getClassesBtn;
    private JButton getSchedules;
    private JButton addTranscriptsBtn;
    private JButton getTranscriptBtn;

    private excelDAO excelDAO;
    public MenuGiaoVuView() {
        addClassesBtn = new JButton();
        addScheduleBtn = new JButton();
        getClassesBtn = new JButton();
        getSchedules = new JButton();
        addTranscriptsBtn = new JButton();
        getTranscriptBtn = new JButton();
        excelDAO = new excelDAO();
        excute();
    }

    public void excute(){
        addClassesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addScheduleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        getClassesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        getSchedules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addTranscriptsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        getTranscriptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void showViewTranscript() {
        setVisible(true);
    }
    private setText(){
        addClassesBtn.setText("Import danh sách lớp");
        addScheduleBtn.setText("Import thời khoá biểu");
        getSchedules.setText("Xem thời khoá biểu");
        getClassesBtn.setText("Xem danh sách lớp");
        addTranscriptsBtn.setText("Import bảng điểm");
        getTranscriptBtn.setText("Xem bảng điểm");
    }
}

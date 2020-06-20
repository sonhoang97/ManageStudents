package View;

import Controller.GiaoVuController;
import ModelEntity.Student;
import org.hibernate.Hibernate;

import javax.swing.JPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClassesStdView extends JFrame {
    private JTable lsStudentTable;
    private JPanel panel1;
    private GiaoVuController gvController;
    private JScrollPane jScrollPaneStudentTable;
    public ClassesStdView() {
        this.gvController = new GiaoVuController();
        this.getLsClassesStd();
    }

    private void getLsClassesStd() {
        String[] columnNames = {"Mssv", "Tên", "Giới tính", "Cmnd", "Lớp"};
        List<Student> students = gvController.getAllStudents();
        Object[][] data = new Object[students.size()][5];
        SpringLayout layout = new SpringLayout();

        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getMssv();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getSex();
            data[i][3] = students.get(i).getCmnd();
            data[i][4] = students.get(i).getClassStd().getClassName();
        }
        lsStudentTable = new JTable(data, columnNames);
        lsStudentTable.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(lsStudentTable);

        this.setTitle("asd");
        this.add(sp);
        this.setSize(400, 300);
        this.setVisible(true);
    }

    public void showClassStdView(){
        setVisible(true);
    }
}

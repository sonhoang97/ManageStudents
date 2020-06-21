package View;

import Controller.GiaoVuController;
import ModelEntity.Student;
import ModelEntity.Subject;
import org.hibernate.Hibernate;

import javax.swing.JPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClassesStdView extends JFrame {
    private JTable lsStudentTable;
    private GiaoVuController gvController;
    private JScrollPane jScrollPaneStudentTable;
    private JButton addStdBtn;

    private SpringLayout layout;
    private  JScrollPane sp;

    private JLabel mssvLabel;
    private JLabel nameLabel;
    private JLabel sexLabel;
    private JLabel cmndLabel;
    private JLabel classIdLabel;

    private JLabel searchLabel;
    private JLabel searchClassLabel;
    private JLabel searchSubjectLabel;

    private JComboBox searchClassCB;
    private JComboBox searchSubjectCB;
    private JButton searchStdBtn;

    private JTextField mssvTF;
    private JTextField nameTF;
    private JTextField sexTF;
    private JTextField cmndTF;
    private JComboBox classIdCB;

    private Student addStd;
    private String searchClassId;
    private String searchSubjectId;

    public ClassesStdView() {
        this.gvController = new GiaoVuController();
        this.addStd = new Student();
        this.lsStudentTable = new JTable();
        this.addStdBtn = new JButton();
        this.searchStdBtn = new JButton();
        this.searchClassId = new String();
        this.searchSubjectId = new String();
        this.getLsClassesStd();

    }

    private void getLsClassesStd() {
        setTextLabelAndTextField();
        classIdCB = new JComboBox(gvController.getClassesId());
        searchClassCB = new JComboBox(gvController.getClassesId());
        searchSubjectCB = new JComboBox(new String[]{"All"});

        searchClassCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSubjectCB.removeAllItems();
                searchSubjectCB.addItem("All");
                JComboBox cb = (JComboBox) e.getSource();
                searchClassId = (String) cb.getSelectedItem();
                List<Subject> sbjs = gvController.getSubjectsByClass(searchClassId);
                String[] subjectsId = new String[sbjs.size()];
                int i=0;
                for(Subject sbj : sbjs)
                {
                    subjectsId[i++] = sbj.getId();
                }
                for(String str: subjectsId) searchSubjectCB.addItem(str);
            }
        });

        classIdCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String classId = (String) cb.getSelectedItem();
                addStd.setClassStd(gvController.getClass(classId));
            }
        });

        searchStdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!searchClassId.isEmpty()&& (searchSubjectId.isEmpty()|| searchSubjectId==null)){
                List<Student> students = gvController.getStudentsByClass(searchClassId);
                showStudent(students);
                }
            }
        });

        List<Student> students = gvController.getAllStudents();
        showStudent(students);

        lsStudentTable.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(lsStudentTable);


        addStdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStd.setMssv(Integer.valueOf(mssvTF.getText()));
                addStd.setPassword(mssvTF.getText());
                addStd.setCmnd(Integer.valueOf(cmndTF.getText()));
                addStd.setSex(sexTF.getText());
                addStd.setName(nameTF.getText());
                if (gvController.addStudent(addStd)) showMessage("Thêm sinh viên thành công!");
                else showMessage("Thêm sinh viên thất bại!");
                List<Student> students = gvController.getAllStudents();
                showStudent(students);
            }
        });


        this.add(sp);
        this.add(mssvLabel);
        this.add(nameLabel);
        this.add(sexLabel);
        this.add(cmndLabel);
        this.add(classIdLabel);
        this.add(mssvTF);
        this.add(nameTF);
        this.add(sexTF);
        this.add(cmndTF);
        this.add(classIdCB);
        this.add(searchClassCB);
        this.add(searchLabel);
        this.add(searchSubjectCB);
        this.add(searchClassLabel);
        this.add(searchSubjectLabel);
        this.add(searchStdBtn);
        this.layout = new SpringLayout();

        this.setLayout(layout);
        this.add(addStdBtn);

        layout.putConstraint(SpringLayout.NORTH, sp, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sp, 240, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, addStdBtn, 170, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, addStdBtn, 70, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, mssvLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, mssvLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, sexLabel, 70, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sexLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, cmndLabel, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cmndLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, classIdLabel, 130, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, classIdLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, mssvTF, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, mssvTF, 70, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, nameTF, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameTF, 70, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, sexTF, 70, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sexTF, 70, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, cmndTF, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cmndTF, 70, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, classIdCB, 130, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, classIdCB, 70, SpringLayout.NORTH, this);


        layout.putConstraint(SpringLayout.NORTH, searchLabel, 230, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchClassLabel, 262, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchClassLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchClassCB, 260, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchClassCB, 40, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchSubjectLabel, 262, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchSubjectLabel, 120, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchSubjectCB, 260, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchSubjectCB, 160, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchStdBtn, 300, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchStdBtn, 10, SpringLayout.NORTH, this);

        this.setSize(750, 480);
        this.setVisible(true);
    }

    public void showClassStdView() {
        setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showStudent(List<Student> students){
        String[] columnNames = {"Mssv", "Tên", "Giới tính", "Cmnd", "Lớp"};
        DefaultTableModel model = (DefaultTableModel) lsStudentTable.getModel();
        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);
        Object[][] data = new Object[students.size()][5];

        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getMssv();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getSex();
            data[i][3] = students.get(i).getCmnd();
            data[i][4] = students.get(i).getClassStd().getId();
            model.addRow(data[i]);
        }
    }

    private void setTextLabelAndTextField(){
        mssvLabel = new JLabel("Mssv");
        nameLabel = new JLabel("Tên");
        sexLabel = new JLabel("Giới tính");
        cmndLabel = new JLabel("Cmnd");
        classIdLabel = new JLabel("Lớp");
        searchLabel = new JLabel("Xem danh sách lớp theo: ");
        searchClassLabel = new JLabel("Lớp: ");
        searchSubjectLabel = new JLabel("Môn: ");
        searchStdBtn.setText("Tìm kiếm");
        mssvTF = new JTextField(15);
        nameTF = new JTextField(15);
        sexTF = new JTextField(5);
        cmndTF = new JTextField(15);
        this.setTitle("Danh sách sinh viên");
        addStdBtn.setText("Thêm mới sinh viên");

    }


}

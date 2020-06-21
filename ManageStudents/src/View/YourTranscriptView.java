package View;

import Controller.GiaoVuController;
import DAO.StudentDAO;
import ModelEntity.Student;
import ModelEntity.Transcript;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class YourTranscriptView extends JFrame{
    private JTable transcriptTable;
    private JScrollPane jScrollPaneTranscriptTable;
    private SpringLayout layout;
    private JScrollPane sp;

    private Transcript trans;
    private JLabel mssvLabel;
    private JLabel mssvBindingLabel;
    private JLabel nameLabel;
    private JLabel nameBindingLabel;
    private JLabel classLabel;
    private JLabel classBindingLabel;
    private JLabel sexLabel;
    private JLabel sexBindingLabel;
    private JLabel cmndLabel;
    private JLabel cmndBingdingLabel;

    private JButton logOutBtn;
    private JButton changePassBtn;

    private StudentDAO studentDAO;
    private Student stdLogin;
    public YourTranscriptView(Student std){
        this.stdLogin = std;
        this.transcriptTable = new JTable();
        this.jScrollPaneTranscriptTable = new JScrollPane();
        studentDAO = new StudentDAO();
        logOutBtn = new JButton();
        changePassBtn = new JButton();
        excute();
    }

    public void excute(){
        setText(stdLogin);
        List<Transcript> trans = new ArrayList<>(stdLogin.getTranscripts());
        showTranscript(trans);
        transcriptTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        sp = new JScrollPane(transcriptTable);

        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView login = new LoginView();
                login.showDialog();
                setVisible(false);
            }
        });

        this.add(sp);
        this.add(mssvLabel);
        this.add(mssvBindingLabel);
        this.add(nameLabel);
        this.add(nameBindingLabel);
        this.add(sexLabel);
        this.add(sexBindingLabel);
        this.add(cmndLabel);
        this.add(cmndBingdingLabel);
        this.add(classLabel);
        this.add(classBindingLabel);
        this.add(logOutBtn);
        this.add(changePassBtn);
        this.layout = new SpringLayout();
        this.setLayout(layout);

        layout.putConstraint(SpringLayout.NORTH, mssvLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, mssvLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, mssvBindingLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, mssvBindingLabel, 50, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 150, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, nameBindingLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameBindingLabel, 190, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, classLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, classLabel, 300, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, classBindingLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, classBindingLabel, 340, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, sexLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sexLabel, 450, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, sexBindingLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sexBindingLabel, 530, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, cmndLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cmndLabel, 590, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, cmndBingdingLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, cmndBingdingLabel, 640, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, sp, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sp, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, changePassBtn, 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, changePassBtn, 500, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, logOutBtn, 150, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, logOutBtn, 500, SpringLayout.NORTH, this);

        this.setSize(750, 250);
        this.setVisible(true);
    }

    private void showTranscript(List<Transcript> transcripts) {
        String[] columnNames = {"Điểm giữa kì", "Điểm cuối kì", "Điểm khác", "Điểm tổng", "Lớp", "Môn", "Tình trạng"};
        DefaultTableModel model = (DefaultTableModel) transcriptTable.getModel();
        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);
        Object[][] data = new Object[transcripts.size()][7];

        for (int i = 0; i < transcripts.size(); i++) {
            data[i][0] = transcripts.get(i).getMidTerm();
            data[i][1] = transcripts.get(i).getEndTerm();
            data[i][2] = transcripts.get(i).getBonus();
            data[i][3] = transcripts.get(i).getPoint();
            data[i][4] = transcripts.get(i).getClassTrans().getId();
            data[i][5] = transcripts.get(i).getSubjectTrans().getSubjectName();
            if (transcripts.get(i).getPoint() < 5) data[i][6] = "Rớt";
            else data[i][6] = "Đậu";
            model.addRow(data[i]);
        }
    }

    private void setText(Student std){
        mssvLabel = new JLabel("Mssv: ");
        mssvBindingLabel = new JLabel(String.valueOf(std.getMssv()));
        nameLabel = new JLabel("Tên: ");
        nameBindingLabel = new JLabel(std.getName());
        classLabel = new JLabel("Lớp: ");
        classBindingLabel = new JLabel(std.getClassStd().getId());
        sexLabel = new JLabel("Giới tính: ");
        sexBindingLabel = new JLabel(std.getSex());
        cmndLabel = new JLabel("Cmnd: ");
        cmndBingdingLabel = new JLabel(String.valueOf(std.getCmnd()));
        logOutBtn.setText("Đăng xuất");
        changePassBtn.setText("Đổi mật khẩu");
    }

    public void showDialog(){
        setVisible(true);
    }
}

package View;

import Controller.GiaoVuController;
import DAO.*;
import ModelEntity.Schedule;
import ModelEntity.Subject;
import ModelEntity.Transcript;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TranscriptView extends JFrame {
    private JTable transcriptTable;
    private GiaoVuController gvController;
    private JScrollPane jScrollPaneTranscriptTable;
    private SpringLayout layout;
    private JScrollPane sp;

    private JComboBox searchClassCB;
    private JComboBox searchSubjectCB;

    private JButton searchTranscriptBtn;
    private JLabel searchTranscriptLabel;
    private JLabel searchClassLabel;
    private JLabel searchSubjectLabel;

    private TranscriptDAO transcriptDAO;
    private String searchClassId;
    private String searchSubjectId;
    private StudentDAO studentDAO;
    private ClassDAO classDAO;
    private SubjectDAO subjectDAO;
    private ChangePointView changePointView;

    private JButton backBtn;
    public TranscriptView() {
        this.studentDAO = new StudentDAO();
        this.classDAO = new ClassDAO();
        this.subjectDAO = new SubjectDAO();
    this.backBtn = new JButton();
        this.transcriptTable = new JTable();
        this.gvController = new GiaoVuController();
        this.jScrollPaneTranscriptTable = new JScrollPane();
        this.transcriptDAO = new TranscriptDAO();
        this.searchClassId = new String();
        this.searchSubjectId = new String();
        this.searchTranscriptBtn = new JButton();
        excute();
    }

    public void excute() {
        setText();
        List<Transcript> transcripts = new ArrayList<>();
        transcripts = transcriptDAO.getTranscripts();
        showTranscript(transcripts);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGiaoVuView menu = new MenuGiaoVuView();
                menu.showViewGiaoVu();
                setVisible(false);
            }
        });

        searchClassCB = new JComboBox(new String[]{"All"});
        String[] searchClassStr = gvController.getClassesId();
        for (String str : searchClassStr) searchClassCB.addItem(str);
        searchClassCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                searchClassId = (String) cb.getSelectedItem();
                searchSubjectCB.removeAllItems();
                searchSubjectCB.addItem("All");
                List<Subject> sbjs = gvController.getSubjectsByClass(searchClassId);
                String[] subjectsId = new String[sbjs.size()];
                int i = 0;
                for (Subject sbj : sbjs) {
                    subjectsId[i++] = sbj.getId();
                }
                for (String str : subjectsId) searchSubjectCB.addItem(str);
            }
        });

        searchSubjectCB = new JComboBox(new String[]{"All"});
        searchSubjectCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                searchSubjectId = (String) cb.getSelectedItem();
            }
        });

        searchTranscriptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Transcript> transcripts = new ArrayList<>();
                if (searchSubjectId == "All" && searchClassId == "All") {
                    transcripts = transcriptDAO.getTranscripts();
                    showTranscript(transcripts);
                } else if (!searchClassId.isEmpty() && !searchSubjectId.isEmpty()) {
                    transcripts = transcriptDAO.getTranscriptsBySubject(searchClassId, searchSubjectId);
                    showTranscript(transcripts);
                } else if (!searchClassId.isEmpty() && searchSubjectId.isEmpty()) {
                    transcripts = transcriptDAO.getTranscriptsBySubject(searchClassId, "");
                    showTranscript(transcripts);
                }
            }
        });

        transcriptTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Transcript tran = new Transcript();
                    Object mssv = table.getModel().getValueAt(row,0);
                    tran.setStudentTrans(studentDAO.getStudent((Integer) mssv));
                    tran.setMidTerm((Double) table.getModel().getValueAt(row,2));
                    tran.setEndTerm((Double) table.getModel().getValueAt(row,3));
                    tran.setBonus((Double) table.getModel().getValueAt(row,4));
                    tran.setPoint((Double) table.getModel().getValueAt(row,5));
                    tran.setClassTrans(classDAO.getClass((String) table.getModel().getValueAt(row,6)));
                    String subjectName = (String) table.getModel().getValueAt(row,7);
                    List<Subject> sbjs = subjectDAO.getSubjectByName(subjectName);
                    tran.setSubjectTrans(sbjs.get(0));
                    changePointView = new ChangePointView(tran);
                    changePointView.showDialog();
                    setVisible(false);
                }
            }
        });
        transcriptTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        transcriptTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        transcriptTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        transcriptTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        transcriptTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        transcriptTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        transcriptTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        transcriptTable.getColumnModel().getColumn(6).setPreferredWidth(60);
        transcriptTable.getColumnModel().getColumn(7).setPreferredWidth(120);
        transcriptTable.getColumnModel().getColumn(8).setPreferredWidth(60);



        sp = new JScrollPane(transcriptTable);
        this.add(sp);
        this.add(backBtn);
        this.add(searchTranscriptLabel);
        this.add(searchClassCB);
        this.add(searchSubjectCB);
        this.add(searchSubjectLabel);
        this.add(searchClassLabel);
        this.add(searchTranscriptBtn);
        this.layout = new SpringLayout();
        this.setLayout(layout);

        layout.putConstraint(SpringLayout.NORTH, searchTranscriptLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchTranscriptLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchClassLabel, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchClassLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchClassCB, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchClassCB, 50, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchSubjectLabel, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchSubjectLabel, 150, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchSubjectCB, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchSubjectCB, 190, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchTranscriptBtn, 80, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchTranscriptBtn, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, sp, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sp, 280, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, backBtn, 120, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, backBtn, 10, SpringLayout.NORTH, this);
        this.setSize(750, 300);
        this.setVisible(true);
    }

    private void showTranscript(List<Transcript> transcripts) {
        String[] columnNames = {"Mssv", "Tên", "Điểm giữa kì", "Điểm cuối kì", "Điểm khác", "Điểm tổng", "Lớp", "Môn", "Tình trạng"};
        DefaultTableModel model = (DefaultTableModel) transcriptTable.getModel();
        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);
        Object[][] data = new Object[transcripts.size()][9];

        for (int i = 0; i < transcripts.size(); i++) {
            data[i][0] = transcripts.get(i).getStudentTrans().getMssv();
            data[i][1] = transcripts.get(i).getStudentTrans().getName();
            data[i][2] = transcripts.get(i).getMidTerm();
            data[i][3] = transcripts.get(i).getEndTerm();
            data[i][4] = transcripts.get(i).getBonus();
            data[i][5] = transcripts.get(i).getPoint();
            data[i][6] = transcripts.get(i).getClassTrans().getId();
            data[i][7] = transcripts.get(i).getSubjectTrans().getSubjectName();
            if (transcripts.get(i).getPoint() < 5) data[i][8] = "Rớt";
            else data[i][8] = "Đậu";
            model.addRow(data[i]);
        }
    }

    public void setText() {
        searchTranscriptLabel = new JLabel("Xem bảng điểm theo: ");
        searchClassLabel = new JLabel("Lớp: ");
        searchSubjectLabel = new JLabel("Môn: ");
        searchTranscriptBtn.setText("Tìm kiếm");
        backBtn.setText("Trở lại");
    }

    public void showViewTranscript() {
        setVisible(true);
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

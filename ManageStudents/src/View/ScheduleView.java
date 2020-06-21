package View;

import Controller.GiaoVuController;
import DAO.ScheduleDAO;
import ModelEntity.Schedule;
import ModelEntity.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ScheduleView extends JFrame {
    private JTable schedulesTable;
    private ScheduleDAO scheduleDAO;
    private GiaoVuController gvController;
    private JScrollPane jScrollPaneScheduleTable;
    private SpringLayout layout;
    private  JScrollPane sp;
    private JComboBox searchClassCB;
    private JButton searchScheduleBtn;
    private JLabel searchScheduleLabel;
    private String searchClassId;
    private JButton backBtn;
    public ScheduleView(){
        this.schedulesTable = new JTable();
        this.searchScheduleBtn = new JButton();
        this.scheduleDAO = new ScheduleDAO();
        this.gvController = new GiaoVuController();
        searchClassId = new String();
        this.backBtn = new JButton();
        excute();
    }

    public void excute(){
        backBtn.setText("Trở lại");
        setText();
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList = scheduleDAO.getSchedules();
        showSchedule(scheduleList);

        searchClassCB = new JComboBox(new String[]{"All"});
        String[] searchClassStr = gvController.getClassesId();
        for(String str: searchClassStr) searchClassCB.addItem(str);
        searchClassCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                searchClassId = (String) cb.getSelectedItem();
            }
        });

        searchScheduleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchClassId.isEmpty()) return;
                if(searchClassId=="All"){
                    List<Schedule> scheduleList = new ArrayList<>();
                    scheduleList = scheduleDAO.getSchedules();
                    showSchedule(scheduleList);
                    return;
                }
                List<Schedule> sbjs = gvController.getSchedulesByClass(searchClassId);
                showSchedule(sbjs);

            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGiaoVuView menu = new MenuGiaoVuView();
                menu.showViewGiaoVu();
                setVisible(false);

            }
        });
        schedulesTable.setBounds(30, 40, 200, 100);
        sp = new JScrollPane(schedulesTable);
        this.add(sp);
        this.add(backBtn);
        this.add(searchScheduleLabel);
        this.add(searchClassCB);
        this.add(searchScheduleBtn);
        this.layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.NORTH, searchScheduleLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchScheduleLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchClassCB, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchClassCB, 40, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, searchScheduleBtn, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, searchScheduleBtn, 150, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, sp, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, sp, 250, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, backBtn, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, backBtn, 10, SpringLayout.NORTH, this);
        this.setSize(750, 300);
        this.setVisible(true);
    }

    private void showSchedule(List<Schedule> scheduleList){
        String[] columnNames = {"Lớp", "Môn", "Phòng"};
        DefaultTableModel model = (DefaultTableModel) schedulesTable.getModel();
        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);
        Object[][] data = new Object[scheduleList.size()][5];

        for (int i = 0; i < scheduleList.size(); i++) {
            data[i][0] = scheduleList.get(i).getClassSch().getId();
            data[i][1] = scheduleList.get(i).getSubjectSch().getSubjectName();
            data[i][2] = scheduleList.get(i).getRoom();
            model.addRow(data[i]);
        }
    }

    public void showViewSchedule(){
        setVisible(true);
    }

    private void setText(){
        searchScheduleBtn.setText("Tìm kiếm");
        searchScheduleLabel = new JLabel("Lớp: ");

    }
}

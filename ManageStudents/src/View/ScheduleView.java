package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScheduleView extends JFrame {
    private JTable schedulesTable;
    public ScheduleView(){
        this.schedulesTable = new JTable();
    }

    public void excute(){
        showSchedule();
    }

    public showSchedule(){
        String[] columnNames = {"Lớp", "Môn", "Phòng"};
        DefaultTableModel model = (DefaultTableModel) schedulesTable.getModel();
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
}

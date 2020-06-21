package View;

import DAO.TranscriptDAO;
import ModelEntity.Transcript;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChangePointView extends JFrame {
    private Transcript trans;
    private JLabel mssvLabel;
    private JLabel mssvBindingLabel;
    private JLabel nameLabel;
    private JLabel nameBindingLabel;
    private JLabel classLabel;
    private JLabel classBindingLabel;
    private JLabel subjectLabel;
    private JLabel subjectBindingLabel;
    private JLabel midTermLabel;
    private JLabel endTermLabel;
    private JLabel bonusLabel;
    private JLabel pointLabel;
    private SpringLayout layout;

    private JTextField midtermBindingText;
    private JTextField endTermBindingText;
    private JTextField bonusBindingText;
    private JTextField pointBindingText;
    private JButton changePointBtn;

    private TranscriptDAO transcriptDAO;
    public ChangePointView(Transcript trans) {
        transcriptDAO = new TranscriptDAO();
        this.trans = trans;
        this.midtermBindingText = new JTextField(4);
        this.endTermBindingText = new JTextField(4);
        this.bonusBindingText = new JTextField(4);
        this.pointBindingText = new JTextField(4);
        changePointBtn = new JButton();
        excute();
    }

    public void excute() {
        setText(this.trans);

        changePointBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transcript transChanged = getText(trans);
                boolean result= transcriptDAO.updateTranscripts(transChanged);
                if(result) showMessage("Cập nhật điểm cho sinh viên thành công!");
                else showMessage("Lỗi!");
                TranscriptView transcriptView = new TranscriptView();
                transcriptView.showViewTranscript();
                setVisible(false);
            }
        });

        this.add(mssvLabel);
        this.add(mssvBindingLabel);
        this.add(nameLabel);
        this.add(nameBindingLabel);
        this.add(midTermLabel);
        this.add(midtermBindingText);
        this.add(endTermLabel);
        this.add(endTermBindingText);
        this.add(bonusLabel);
        this.add(bonusBindingText);
        this.add(pointLabel);
        this.add(pointBindingText);
        this.add(subjectLabel);
        this.add(subjectBindingLabel);
        this.add(classLabel);
        this.add(classBindingLabel);
        this.add(changePointBtn);

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

        layout.putConstraint(SpringLayout.NORTH, subjectLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, subjectLabel, 450, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, subjectBindingLabel, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, subjectBindingLabel, 490, SpringLayout.NORTH, this);


        layout.putConstraint(SpringLayout.NORTH, midTermLabel, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, midTermLabel, 10, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, midtermBindingText, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, midtermBindingText, 90, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, endTermLabel, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, endTermLabel, 150, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, endTermBindingText, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, endTermBindingText, 230, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, bonusLabel, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, bonusLabel, 290, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, bonusBindingText, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, bonusBindingText, 360, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, pointLabel, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, pointLabel, 430, SpringLayout.NORTH, this);


        layout.putConstraint(SpringLayout.NORTH, pointBindingText, 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, pointBindingText, 500, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, changePointBtn, 70, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, changePointBtn, 10, SpringLayout.NORTH, this);

        this.setSize(650, 150);
        this.setVisible(true);
    }
    public void showDialog(){
        setVisible(true);
    }
    public void setText(Transcript trans) {
        mssvLabel = new JLabel("Mssv: ");
        mssvBindingLabel = new JLabel(String.valueOf(trans.getStudentTrans().getMssv()));
        nameLabel = new JLabel("Tên: ");
        nameBindingLabel = new JLabel(trans.getStudentTrans().getName());
        classLabel = new JLabel("Lớp: ");
        classBindingLabel = new JLabel(trans.getClassTrans().getId());
        subjectLabel = new JLabel("Môn: ");
        subjectBindingLabel = new JLabel(trans.getSubjectTrans().getSubjectName());
        midTermLabel = new JLabel("Điểm giữa kì: ");
        endTermLabel = new JLabel("Điểm cuối kì: ");
        bonusLabel = new JLabel("Điểm khác: ");
        pointLabel = new JLabel("Điểm tổng: ");

        midtermBindingText.setText(String.valueOf(trans.getMidTerm()));
        endTermBindingText.setText(String.valueOf(trans.getEndTerm()));
        bonusBindingText.setText(String.valueOf(trans.getBonus()));
        pointBindingText.setText(String.valueOf(trans.getPoint()));

        changePointBtn.setText("Đổi điểm");
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public Transcript getText(Transcript trans){
        trans.setMidTerm(Double.valueOf( midtermBindingText.getText()));
        trans.setEndTerm(Double.valueOf( endTermBindingText.getText()));
        trans.setBonus(Double.valueOf( bonusBindingText.getText()));
        trans.setPoint(Double.valueOf( pointBindingText.getText()));
        return trans;
    }
}

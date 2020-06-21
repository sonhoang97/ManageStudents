package View;

import DAO.StudentDAO;
import ModelEntity.Student;
import ModelView.UserViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

public class LoginView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel userNameLabel;
    private JLabel passwordlabel;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JButton loginBtn;
    private JButton cancelBtn;
    private StudentDAO stdDAO;
    private ClassesStdView classesStdView;
    private ScheduleView scheduleView;
    private TranscriptView transcriptView;
    public LoginView() {
        initComponents();
        this.stdDAO = new StudentDAO();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userNameLabel = new JLabel("UserName");
        passwordlabel = new JLabel("Password");
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginBtn = new JButton();
        cancelBtn = new JButton();
        loginBtn.setText("Login");
        loginBtn.addActionListener(this);
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(this);
        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        // tạo đối tượng panel để chứa các thành phần của màn hình login
        panel.setSize(400, 300);
        panel.setLayout(layout);
        panel.add(userNameLabel);
        panel.add(passwordlabel);
        panel.add(userNameField);
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(cancelBtn);
        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, userNameLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, userNameLabel, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordlabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordlabel, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, userNameField, 80, SpringLayout.WEST, userNameLabel);
        layout.putConstraint(SpringLayout.NORTH, userNameField, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 80, SpringLayout.WEST, passwordlabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, loginBtn, 80, SpringLayout.WEST, passwordlabel);
        layout.putConstraint(SpringLayout.NORTH, loginBtn, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, cancelBtn, 160, SpringLayout.WEST, passwordlabel);
        layout.putConstraint(SpringLayout.NORTH, cancelBtn, 130, SpringLayout.NORTH, panel);
        // add panel tới JFrame
        this.add(panel);
        this.pack();
        // cài đặt các thuộc tính cho JFrame
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public UserViewModel getUser() {
        return new UserViewModel(userNameField.getText(),
                String.copyValueOf(passwordField.getPassword()));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Login") {
            UserViewModel user = getUser();
            String gv = "giaovu";
            if (user.Username.equals(gv) && user.Password.equals(gv)) {
                MenuGiaoVuView menu = new MenuGiaoVuView();
                menu.showViewGiaoVu();
            } else if (stdDAO.checkLogin(user)) {
                Student std = stdDAO.getStudent(Integer.valueOf(user.Username));
                YourTranscriptView ur = new YourTranscriptView(std);
                ur.showDialog();
                setVisible(false);
            } else {
                showMessage("username hoặc password không đúng.");
            }
        }
        else if(e.getActionCommand()=="Cancel"){
            setVisible(false);
        }
    }

}
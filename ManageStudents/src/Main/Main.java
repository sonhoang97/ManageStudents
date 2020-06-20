package Main;

import DAO.*;
import ModelEntity.Schedule;
import View.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LoginView view = new LoginView();
        view.setVisible(true);
    }
}

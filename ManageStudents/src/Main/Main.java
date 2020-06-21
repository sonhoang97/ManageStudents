package Main;

import DAO.*;
import ModelEntity.Schedule;
import ModelEntity.Transcript;
import View.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LoginView view = new LoginView();
        view.setVisible(true);
//          excelDAO ex = new excelDAO();
//          TranscriptDAO transDAO = new TranscriptDAO();
//          List<Transcript> trans = ex.getTranscriptExcel();
//          for(Transcript tran: trans){
//              transDAO.addTranscript(tran);
//          }
    }
}

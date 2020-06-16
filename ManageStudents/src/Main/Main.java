package Main;

import DAO.StudentDAO;
import Model.Student;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> ds= StudentDAO.layDanhSachSinhVien();
        for(int i=0; i<ds.size(); i++){
            Student sv=ds.get(i);
            System.out.println("MSSV: "+sv.getMssv());
            System.out.println("Họ và tên: "+sv.getName());
            System.out.println("Giới tính: " +
                    sv.isSex());
            System.out.println("Cmnd: "+ sv.getCmnd());
        }
    }
}

package DAO;

import ModelEntity.Student;
import ModelView.UserViewModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class StudentDAO {
    public StudentDAO() {

    }

    public List<Student> getStudents() {
        List<Student> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select std from Student std";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }

    public Student getStudent(int mssv) {
        Student student = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            student = (Student) session.get(Student.class, mssv);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return student;
    }

    public boolean addStudent(Student std) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (getStudent(std.getMssv()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(std);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public boolean checkLogin(UserViewModel user){
        if(user == null) return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select std from Student std where  std.mssv = :mssv and std.password = :password";
            Query query = session.createQuery(hql);
            List<Object[]> StdData = query.setInteger("mssv", Integer.valueOf(user.Username))
                    .setString("password", user.Password).list();
            if(StdData.isEmpty()) return false;
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return false;
    }
}

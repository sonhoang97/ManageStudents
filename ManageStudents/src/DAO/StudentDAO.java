package DAO;

import Model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class StudentDAO {
    public StudentDAO() {

    }

    public List<Student> getListStudent() {
        List<Student> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select std from Student std";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
//Log the exception
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
}

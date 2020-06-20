package DAO;

import ModelEntity.Class;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ClassDAO {
    public ClassDAO(){}

    public boolean AddClassDB(Class cls){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(cls);
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

    public Class getClass(String classId){
        Class cls = new Class();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            cls = (Class) session.get(Class.class, classId);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return cls;
    }
}

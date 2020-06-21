package DAO;

import ModelEntity.Class;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    public ClassDAO(){}

    public boolean AddClassDB(Class cls){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(getClass(cls.getId())!=null) return false;

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

    public List<Class> getAllClasses(){
        List<Class> clses = new ArrayList<Class>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select cls from Class cls";
            Query query = session.createQuery(hql);
            clses = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return clses;
    }
}

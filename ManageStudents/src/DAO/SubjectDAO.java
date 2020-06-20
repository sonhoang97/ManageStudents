package DAO;

import ModelEntity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class SubjectDAO {
    public SubjectDAO(){}

    public boolean AddSubjectDB(Subject sbj){
        Session session = HibernateUtil.getSessionFactory().openSession();
//        if (getStudent(std.getMssv()) != null) {
//            return false;
//        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sbj);
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

    public Subject getSubjectDB(String subjectId){
        Subject sbj = new Subject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sbj = (Subject) session.get(Subject.class, subjectId);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return sbj;
    }
}

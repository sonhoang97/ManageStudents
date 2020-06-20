package DAO;

import ModelEntity.Schedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ScheduleDAO {
    public ScheduleDAO(){}

//    public Schedule getSchedule(String className, String SubjectId){
//        Schedule sch = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            sch = (Schedule) session.get(Schedule.class, className);
//        } catch (HibernateException ex) {
//            //Log the exception
//            System.err.println(ex);
//        } finally {
//            session.close();
//        }
//        return student;
//    }

    public boolean addSchedule(Schedule sch){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sch);
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

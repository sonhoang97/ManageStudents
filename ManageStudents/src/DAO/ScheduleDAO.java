package DAO;

import ModelEntity.Schedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    public ScheduleDAO(){}

    public List<Schedule> getSchedules(){
        List<Schedule> schs = new ArrayList<Schedule>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sch from Schedule sch";
            Query query = session.createQuery(hql);
            schs = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return schs;
    }

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

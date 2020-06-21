package DAO;

import ModelEntity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

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

    public List<Subject> getSubjectsByClass(String classId){
        if(classId == null) return new ArrayList<Subject>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sch.subjectSch from Schedule sch where sch.classSch.Id = :classId";
            Query query = session.createQuery(hql);
            List<Subject> subjects = query.setString("classId", classId).list();
            return subjects;

        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return new ArrayList<Subject>();
    }

    public List<Subject> getSubjectByName(String subjectName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sbj from Subject sbj where sbj.SubjectName = :subjectName";
            Query query = session.createQuery(hql);
            List<Subject> subjects = query.setString("subjectName", subjectName).list();
            return subjects;

        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return new ArrayList<Subject>();
    }
}

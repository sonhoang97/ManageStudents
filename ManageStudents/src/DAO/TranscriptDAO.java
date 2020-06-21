package DAO;

import ModelEntity.Student;
import ModelEntity.Transcript;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class TranscriptDAO {

    public TranscriptDAO() {
    }

    public boolean addTranscript(Transcript tran) {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        if (getStudent(std.getMssv()) != null) {
//            return false;
//        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(tran);
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

    public List<Transcript> getTranscripts() {
        List<Transcript> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select trans from Transcript trans";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }

    public List<Transcript> getTranscriptsBySubject(String classId, String subjectId) {
        List<Transcript> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            if (subjectId == "All" || subjectId.isEmpty()) {
                String hql = "select trans from Transcript trans where trans.classTrans.id = :classId";
                Query query = session.createQuery(hql);
                ds = query.setString("classId", classId).list();
            } else {
                String hql = "select trans from Transcript trans where trans.classTrans.id = :classId and trans.subjectTrans.id = :subjectId";
                Query query = session.createQuery(hql);
                ds = query.setString("classId", classId)
                        .setString("subjectId", subjectId).list();

            }
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }

    public List<Integer> getIdTranscriptsBySubjectStd(int mssv, String subjectId) {
        List<Integer> ds = new ArrayList<Integer>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

                String hql = "select trans.id from Transcript trans where trans.studentTrans.id = :mssv and trans.subjectTrans.id = :subjectId";
                Query query = session.createQuery(hql);
                ds = query.setInteger("mssv", mssv)
                        .setString("subjectId", subjectId).list();

        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }

    public boolean updateTranscripts(Transcript tran){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            List<Integer> temp = getIdTranscriptsBySubjectStd(tran.getStudentTrans().getMssv(),tran.getSubjectTrans().getId());
            tran.setId(temp.get(0));
            session.update(tran);
            transaction = session.beginTransaction();
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return false;
    }
}

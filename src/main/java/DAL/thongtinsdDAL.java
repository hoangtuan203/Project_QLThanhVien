/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author PHY
 */
public class thongtinsdDAL {

    public static List<thongtinsd> selectAll() {
        List<thongtinsd> list = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("from thongtinsd");
            list = query.getResultList();

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static void add(thongtinsd t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();

            session.save(t);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    public static void update(thongtinsd t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();

            session.update(t);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    public static void delete(thongtinsd t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();

            session.delete(t);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
    public static void traTB(int maTB) {
        List<thongtinsd> thongtinsdList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("SELECT td FROM thongtinsd td WHERE td.tgTra IS NULL AND maTB != 0 AND maTB IS NOT NULL AND maTB =: maTB",thongtinsd.class);
            query.setParameter("maTB", maTB);
            thongtinsdList = query.getResultList();
            thongtinsd ttsd = thongtinsdList.get(0);
             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ttsd.setTgTra(timestamp);
            update(ttsd);
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    
    
    public static void main(String[] args) {
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        thongtinsdDAL dal = new thongtinsdDAL();
        dal.traTB(1000002);
    }

}

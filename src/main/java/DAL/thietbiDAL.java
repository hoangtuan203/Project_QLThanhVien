/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

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
public class thietbiDAL {

    public static List<thietbi> selectAll() {
        List<thietbi> list = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("from thietbi");
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

    public static void add(thietbi t) {
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

    public static void update(thietbi t) {
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

    public static void delete(thietbi t) {
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

    public static int getNextDeviceID() {
        int nextDeviceID = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createSQLQuery("SELECT MAX(MaTB) FROM thietbi");
            Integer maxId = (Integer) query.getSingleResult();
            if (maxId != null) {
                nextDeviceID = maxId + 1;
            } else {
                nextDeviceID = 10000001;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return nextDeviceID;
    }

    public static List<thietbi> ListByDeviceID(int deviceId) {
        List<thietbi> deviceList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("from thietbi where MaTB like :id");
            query.setParameter("id", "%" + deviceId + "%");
            deviceList = query.getResultList();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return deviceList;
    }

    public static List<thietbi> ListByDeviceName(String deviceName) {
        List<thietbi> deviceList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("from thietbi where TenTB like :name");
            query.setParameter("name", "%" + deviceName + "%");
            deviceList = query.getResultList();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return deviceList;
    }

    public static List<thietbi> ListByDeviceDescription(String deviceDescription) {
        List<thietbi> deviceList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("from thietbi where MoTaTB like :name");
            query.setParameter("name", "%" + deviceDescription + "%");
            deviceList = query.getResultList();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return deviceList;
    }

    public static List<thietbi> getDevicesByBorrowDateAndReturnDate() {
        List<thietbi> deviceList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("SELECT DISTINCT tb FROM thietbi tb INNER JOIN thongtinsd ttsd ON tb.maTB = ttsd.maTB");
            deviceList = query.getResultList();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return deviceList;
    }

}

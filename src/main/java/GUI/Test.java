/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package GUI;

import DAL.thanhvien;
import DAL.thietbi;
import DAL.thongtinsd;
import DAL.xuly;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ASUS
 */
public class Test {

    private static SessionFactory factory;

    public Test(SessionFactory factory) {
        this.factory = factory;
    }

    public static void main(String[] args) {
        SessionFactory factory = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        if (factory != null) {
            Test hb = new Test(factory);
<<<<<<< HEAD
            hb.listHandle();
=======
            hb.listDevice();
            hb.add();
            System.out.println("List new :");
            hb.listDevice();

>>>>>>> 89b0c7306aa7eb40e22b852977905cc5a61d2fd2
        } else {
            System.out.println("Failed to initialize SessionFactory.");
        }
    }
    public void listDevice() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM thietbi").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                thietbi employee = (thietbi) iterator.next();
                System.out.print("First Name: " + employee.getMaTB());
                System.out.print("  Last Name: " + employee.getTenTB());
                System.out.println("  Salary: " + employee.getMoTaTB());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void listHandle() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM xuly").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                xuly employee = (xuly) iterator.next();
                System.out.print("ma XL: " + employee.getMaXL());
                System.out.print("ma TV: " + employee.getMaTV());
                System.out.println("  Hinh thuc: " + employee.getHinhThucXL());
                System.out.print("so Tien: " + employee.getSoTien());
                System.out.print("  ngay xl: " + employee.getNgayXL());
                System.out.println("  trang thai: " + employee.getTrangThaiXL());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void listInformationUsage() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM thongtinsd").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                thongtinsd employee = (thongtinsd) iterator.next();
                System.out.print("ma TT: " + employee.getMaTT());
                System.out.print("ma TV: " + employee.getMaTV());
                System.out.println("Ma Tb: " + employee.getMaTB());
                System.out.print("date vao: " + employee.getTgVao());
                System.out.print("date muon: " + employee.getTgMuon());
                System.out.println("  date tra: " + employee.getTgTra());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void listThanhVien() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM thanhvien").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
                thanhvien employee = (thanhvien) iterator.next();
                System.out.print("ma TV: " + employee.getMaTV());
                System.out.print("ho ten: " + employee.getHoTen());
                System.out.println("khoa: " + employee.getKhoa());
                System.out.print("nganh : " + employee.getNganh());
                System.out.print("sdt: " + employee.getSdt());
              
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void add(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            thietbi tb = new thietbi();
            int a = 1000004;
            tb.setMaTB(a);
            tb.setTenTB("a");
            tb.setMoTaTB("test");
            session.save(tb);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
}

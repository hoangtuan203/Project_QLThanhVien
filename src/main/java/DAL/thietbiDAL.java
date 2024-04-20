/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import BUS.thietbiBUS;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

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

    public static void importExcelToDatabase(File excelFile) {
        try (FileInputStream excelFIS = new FileInputStream(excelFile); 
             BufferedInputStream excelBIS = new BufferedInputStream(excelFIS); 
             XSSFWorkbook excelImportToJTable = new XSSFWorkbook(excelBIS)) {

            XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

            for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                XSSFRow excelRow = excelSheet.getRow(row);
                Object[] rowData = new Object[5];

                for (int column = 0; column < 3; column++) {
                    XSSFCell excelCell = excelRow.getCell(column);
                    if (column == 0 && excelCell != null && excelCell.getCellType() == CellType.NUMERIC) {
                        rowData[column + 1] = (int) excelCell.getNumericCellValue();
                    } else if (excelCell != null) {
                        rowData[column + 1] = excelCell.getStringCellValue();
                    }
                }
                
                thietbi tb = new thietbi((int) rowData[1], (String) rowData[2], (String) rowData[3]);
                
                add(tb);
            }
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
    public static List<thietbi> getDevicesChuaDuocMuon() {
        List<thietbi> deviceList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("SELECT t FROM thietbi t WHERE t.maTB NOT IN (SELECT td.maTB FROM thongtinsd td WHERE td.tgTra IS NULL AND maTB != 0 AND maTB IS NOT NULL)");
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
    public static void main(String[] args) {
        thietbiDAL dal = new thietbiDAL();
         List<thietbi> deviceList = dal. getDevicesChuaDuocMuon();
         
         for (thietbi d : deviceList) {
             System.out.println(d.getMaTB() + d.getTenTB());
        }
    }
}

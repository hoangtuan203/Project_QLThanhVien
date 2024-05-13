/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class thanhvienDAL {
    public static List<thanhvien> getAll() {
        List<thanhvien> list = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("from thanhvien");
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

    public static boolean add(thanhvien t) {
        boolean flag = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();

            session.save(t);

            tr.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return flag;
    }

    public static boolean update(thanhvien updatedThanhVien) {
        boolean flag = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;

        try {
            tr = session.beginTransaction();
            thanhvien thanhVien = session.get(thanhvien.class, updatedThanhVien.getMaTV());
            if (thanhVien != null) {
                thanhVien.setHoTen(updatedThanhVien.getHoTen());
                thanhVien.setKhoa(updatedThanhVien.getKhoa());
                thanhVien.setNganh(updatedThanhVien.getNganh());
                thanhVien.setEmail(updatedThanhVien.getEmail());
                thanhVien.setSdt(updatedThanhVien.getSdt());
                thanhVien.setPassword(updatedThanhVien.getPassword());

                session.update(thanhVien);
                tr.commit();
                flag = true;
                System.out.println("ThanhVien updated successfully.");
            } else {
                System.out.println("ThanhVien with maTV " + updatedThanhVien.getMaTV() + " not found.");
            }
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }

    public static boolean delete(int maTV) {
        if (checkMaTVExistsTTSD(maTV) || checkMaTVExistsXL(maTV)) {
            return false;
        }
        boolean flag = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM thanhvien WHERE maTV = :maTV");
            query.setParameter("maTV", maTV);
            int rowsAffected = query.executeUpdate();

            tr.commit();
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return flag;
    }

    public static boolean checkMaTVExistsTTSD(int maTV) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT COUNT(tv) FROM thongtinsd tv WHERE tv.maTV = :maTV";
            Query query = session.createQuery(hql, Long.class);
            query.setParameter("maTV", maTV);
            Long count = (Long) query.getSingleResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean checkMaTVExistsXL(int maTV) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT COUNT(tv) FROM xuly tv WHERE tv.maTV = :maTV";
            Query query = session.createQuery(hql, Long.class);
            query.setParameter("maTV", maTV);
            Long count = (Long) query.getSingleResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public static thanhvien getThanhVienByMaTV(int maTV) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            thanhvien thanhVien = session.get(thanhvien.class, maTV);
            return thanhVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public static boolean checkThanhVien(int maTV) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(
                    "SELECT COUNT(*) FROM thanhvien WHERE maTV = :maTV ",
                    Long.class);
            query.setParameter("maTV", maTV);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkViPham(int maTV) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(
                    "SELECT COUNT(*) FROM xuly WHERE maTV = :maTV AND TrangThaiXL = 1",
                    Long.class);
            query.setParameter("maTV", maTV);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteThanhVienByTriggerYear(String triggerYear) {
        boolean flag = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Convert Integer year to String
            String yearString = String.valueOf(triggerYear);

            Query query = session.createQuery("DELETE FROM thanhvien WHERE SUBSTRING(MaTV, 3, 2) = :yearString");
            query.setParameter("yearString", yearString);

            int rowsAffected = query.executeUpdate();
            tx.commit();

            flag = rowsAffected > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }

    public static boolean importFromExcel(File file) {
        boolean flag = false;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // Mở session từ SessionFactory
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            // Duyệt qua các hàng trong tệp Excel và thêm dữ liệu vào cơ sở dữ liệu
            for (org.apache.poi.ss.usermodel.Row row : sheet) {
                // Bỏ qua hàng tiêu đề
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Đọc dữ liệu từ các cột
                int maTV = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
                String HoTen = dataFormatter.formatCellValue(row.getCell(1));
                String Khoa = dataFormatter.formatCellValue(row.getCell(2));
                String Nganh = dataFormatter.formatCellValue(row.getCell(3));
                String SDT = dataFormatter.formatCellValue(row.getCell(4), formulaEvaluator);
                String Password = dataFormatter.formatCellValue(row.getCell(5));
                String Email = dataFormatter.formatCellValue(row.getCell(6));

                // Tạo đối tượng xuly
                thanhvien t = new thanhvien(maTV, HoTen, Khoa, Nganh, Integer.parseInt(SDT), Password, Email);

                // Lưu đối tượng vào cơ sở dữ liệu
                session.save(t);
                flag = true;
            }

            // Commit transaction và đóng session
            transaction.commit();
            session.close();

            // Đóng workbook và inputStream
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static void main(String args[]) {
        thanhvienDAL dal = new thanhvienDAL();
        // Vegetable obj = dal.getVegetable(1);
        // System.out.println(obj.getVegetableName());
        // List list = dal.getAll();
        //
        // for (Iterator iterator = list.iterator(); iterator.hasNext();){
        // thanhvien t = (thanhvien) iterator.next();
        // System.out.println("ID: " + t.getMaTV());
        // System.out.println("Name: " + t.getHoTen());
        //
        // }
        // System.out.println(dal.delete(2));
        // dal.deleteThanhVienByTriggerYear("11");
        System.out.println("1120010007:" + dal.checkThanhVien(1120010007));

    }

 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author PHY
 */
public class xulyDAL {

    public static List<xuly> selectAll() {
        List<xuly> list = new ArrayList();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                // Thuc thi cau lenh HQL
                String hql = "from xuly";
                Query query = session.createQuery(hql);
                list = query.getResultList();
                tr.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Integer> selecttvxl() {
        List<Integer> list = new ArrayList();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                // Thuc thi cau lenh HQL
                String hql = "SELECT thongtinsd.MaTV "
                        + "FROM thongtinsd "
                        + "JOIN thanhvien ON thongtinsd.MaTV = thanhvien.MaTV "
                        + "JOIN xuly ON thanhvien.MaTV = xuly.MaTV "
                        + "WHERE DATE(TGTra) = CURRENT_DATE() AND xuly.TrangThaiXL = 0";
                Query query = session.createQuery(hql);
                list = query.getResultList();
                tr.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static xuly selectxulybymaxl(int maxl) {
        xuly result = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

                // Thực thi câu lệnh HQL
                String hql = "FROM xuly WHERE MaXl = :maxl";
                Query query = session.createQuery(hql);
                query.setParameter("maxl", maxl);
                List<xuly> list = query.getResultList();

                // Kiểm tra nếu danh sách không rỗng thì lấy phần tử đầu tiên
                if (!list.isEmpty()) {
                    result = list.get(0);
                }

                tr.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<thongtinsd> selectalltv() {
        List<thongtinsd> list = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                // Thực thi câu lệnh HQL
                String hql = " FROM thongtinsd WHERE TGMuon > 0";
                Query query = session.createQuery(hql);
                list = query.getResultList();
                tr.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean deleteXL(xuly t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                // Thuc thi cau lenh HQL
                session.delete(t);
                tr.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addXL(xuly t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                session.save(t);
                tr.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<xuly> searchXL(String keyword) {
        List<xuly> searchResult = new ArrayList<>();

        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();

                // Sử dụng câu truy vấn HQL (Hibernate Query Language) để tìm kiếm các bản ghi
                Query query = session.createQuery("FROM xuly WHERE MaXL LIKE :keyword OR MaTV LIKE :keyword OR HinhThucXL LIKE :keyword OR SoTien LIKE :keyword OR NgayXL LIKE :keyword OR TrangThaiXL LIKE :keyword");
                query.setParameter("keyword", "%" + keyword + "%");
                List result = query.getResultList();

                for (Object obj : result) {
                    if (obj instanceof xuly) {
                        xuly x = (xuly) obj;
                        searchResult.add(x);
                    }
                }

                tr.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchResult;
    }

    public static boolean updateXL(xuly t) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                session.update(t);
                tr.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

// Phương thức để tính toán giá trị mới cho trường maXL


package DAL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ThongKeDAL {

    public ThongKeDAL() {

    }

    public static List<Date> layDanhSachNgay() {
        List<Date> danhSachNgay = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Bắt đầu giao dịch
            session.beginTransaction();

            // Sử dụng HQL (Hibernate Query Language) để truy vấn cơ sở dữ liệu
            Query<Date> query = session.createQuery("SELECT tv.tgVao FROM thongtinsd tv", Date.class);
            danhSachNgay = query.getResultList();

            // Kết thúc giao dịch
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachNgay;
    }

    public List<Integer> getCountOfMembersForEachDate() {
        List<Integer> counts = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Bắt đầu giao dịch
            session.beginTransaction();

            // Sử dụng HQL để truy vấn cơ sở dữ liệu và lấy danh sách ngày và số lượng bản
            // ghi tương ứng
            Query<Object[]> query = session
                    .createQuery("SELECT tv.tgVao, COUNT(*) FROM thongtinsd as tv GROUP BY tv.tgVao", Object[].class);

            List<Object[]> resultList = query.getResultList();

            // Duyệt qua danh sách kết quả và thêm số lượng thành viên vào danh sách counts
            for (Object[] result : resultList) {
                Long dateCount = (Long) result[1];
                counts.add(dateCount.intValue());
            }

            // Kết thúc giao dịch
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counts;
    }

    public List<Date> getFilteredDateVao(Date dateStart, Date dateEnd) {
        List<Date> thoiDiemList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Date> query = session.createQuery(
                    "SELECT tv.tgVao FROM thongtinsd as tv WHERE tv.tgVao >= :start AND tv.tgVao <= :end GROUP BY tv.tgVao\r\n"
                            + //
                            "",
                    Date.class);
            query.setParameter("start", dateStart);
            query.setParameter("end", dateEnd);
            thoiDiemList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thoiDiemList;
    }

    public List<Integer> getFilteredNumberOfMembersForDate(Date dateStart, Date dateEnd) {
        List<Integer> counts = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Object[]> query = session.createQuery(
                    "SELECT tv.tgVao, COUNT(*) FROM thongtinsd as tv WHERE tv.tgVao BETWEEN :start AND :end GROUP BY tv.tgVao",
                    Object[].class);
            query.setParameter("start", dateStart);
            query.setParameter("end", dateEnd);
            List<Object[]> resultList = query.getResultList();

            // Xử lý kết quả truy vấn để lấy số lượng mỗi ngày
            for (Object[] result : resultList) {
                Long dateCount = (Long) result[1];
                counts.add(dateCount.intValue());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counts;
    }

    public static void main(String[] args) {
        ThongKeDAL tk = new ThongKeDAL();

        // Chuyển các đối số từ kiểu String thành kiểu Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart;
        Date dateEnd;
        try {
            dateStart = dateFormat.parse("2024-04-03");
            dateEnd = dateFormat.parse("2024-04-16");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Gọi phương thức để lấy danh sách số lượng thành viên cho mỗi ngày trong
        // khoảng thời gian đã chỉ định
        List<Date> danhSachNgay = tk.getFilteredDateVao(dateStart, dateEnd);

        // In ra số lượng thành viên cho mỗi ngày
        System.out.println("list date:");
        for (Date soLuong : danhSachNgay) {
            System.out.println(soLuong);
        }
      
    }

}

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

            // Sử dụng HQL để truy vấn cơ sở dữ liệu và lấy danh sách ngày và số lượng bản ghi tương ứng
            Query<Object[]> query = session.createQuery("SELECT tv.tgVao, COUNT(*) FROM thongtinsd as tv GROUP BY tv.tgVao", Object[].class);

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

    public static void main(String[] args) {
        ThongKeDAL tk = new ThongKeDAL();

        List<Integer> danhSachNgay = tk.getCountOfMembersForEachDate();

        // In ra các ngày
        System.out.println("count:");
        for (Integer ngay : danhSachNgay) {
            System.out.println(ngay);
        }
//
//        // Tạo một ngày để kiểm tra
//        Date dateToCheck = new Date(); // Sử dụng ngày hiện tại, hoặc bạn có thể tạo ngày khác
//
//        // Gọi hàm để lấy số lượng thành viên vào trong ngày đã chọn
//        int numberOfMembers = tk.getNumberOfMembersForDate(dateToCheck);
//
//        // In ra kết quả
//        System.out.println("Số lượng thành viên vào trong ngày " + dateToCheck + " là: " + numberOfMembers);
    }

}

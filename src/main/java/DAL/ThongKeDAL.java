package DAL;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ThongKeDAL {

    public ThongKeDAL() {

    }

    private int getNumberOfMembersForDate() {
        int count = 0;
        try {
            // Mở phiên làm việc với Hibernate
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Truy vấn cơ sở dữ liệu để lấy số lượng thành viên vào khu học tập cho mỗi ngày
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM ThanhVien WHERE DATE(ngay_vao_khu) <= CURRENT_TIMESTAMP()", Long.class);
            Long result = query.uniqueResult();
            count = result != null ? result.intValue() : 0;

            // Commit giao dịch và đóng phiên làm việc
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        ThongKeDAL tk = new ThongKeDAL();
        System.out.println("count:" + tk.getNumberOfMembersForDate());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.ThongKeDAL;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ThongKeBUS {

    ThongKeDAL thongKeDAL = new ThongKeDAL();

    public static List<Date> getAllDateVao() {
        return ThongKeDAL.layDanhSachNgay();
    }

    public List<Integer> getNumberOfMembersForDate() {
        return thongKeDAL.getCountOfMembersForEachDate();
    }

    public List<Date> getFilteredDateVao(Date dateStart, Date dateEnd) {
        return thongKeDAL.getFilteredDateVao(dateStart, dateEnd);
    }

    public List<Integer> getFilteredNumberOfMembersForDate(Date dateStart, Date dateEnd) {
        return thongKeDAL.getFilteredNumberOfMembersForDate(dateStart, dateEnd);
    }

    public List<Integer> getAllTrangThaiXL() {
        return thongKeDAL.getAllTrangThaiXL();
    }

    public int getCountForStatus(int status) {
        return thongKeDAL.getCountForStatus(status);
    }

    public int getTotalAmount() {
        return thongKeDAL.getTotalAmount();
    }

    public List<String> getAllKhoa() {
        return thongKeDAL.getAllKhoa();
    }

    public List<String> getAllNganh() {
        return thongKeDAL.getAllNganh();
    }

}

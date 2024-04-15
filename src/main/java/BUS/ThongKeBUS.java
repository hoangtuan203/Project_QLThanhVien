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
     public List<Integer> getNumberOfMembersForDate(){
         return thongKeDAL.getCountOfMembersForEachDate();
     }

   
}

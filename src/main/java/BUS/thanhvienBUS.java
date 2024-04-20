/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.thanhvien;
import DAL.thanhvienDAL;
import java.io.File;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class thanhvienBUS {
        public static List<thanhvien>  getAll() {
        return thanhvienDAL.getAll();
    }
     public static boolean checkThanhVien(int maTV){
         return thanhvienDAL.checkThanhVien(maTV);
     }
     public static boolean checkViPham(int maTV){
         return thanhvienDAL.checkViPham(maTV);
     }
    public static boolean importFromExcel(File file)
    {
        return thanhvienDAL.importFromExcel(file);
    }
    public static boolean add(thanhvien t) {
        return thanhvienDAL.add(t);
    }

   public static boolean update(thanhvien t) {
        return thanhvienDAL.update(t);
    }
    
    public static boolean delete(int maTV){
       return thanhvienDAL.delete(maTV);
    }
    public static thanhvien getThanhVienByMaTV(int maTV){
       return thanhvienDAL.getThanhVienByMaTV(maTV);
    }
    public static boolean deleteThanhVienByTriggerYear(String triggerYear){
       return thanhvienDAL.deleteThanhVienByTriggerYear(triggerYear);
    }
    public static void main(String[] args) {
        thanhvienBUS t = new thanhvienBUS();
        System.out.println( t.delete(2));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.thongtinsd;
import java.util.List;
import DAL.thongtinsdDAL;

/**
 *
 * @author Cong anh
 */
public class thongtinsdBUS {

    public static List<thongtinsd> getAllDevice() {
        return thongtinsdDAL.selectAll();
    }

    public static void add(thongtinsd tb) {
        thongtinsdDAL.add(tb);
    }

    
    
    public static void delete(thongtinsd tb){
        thongtinsdDAL.delete(tb);
    }
    
    public static void update(thongtinsd tb){
        thongtinsdDAL.update(tb);
    }
     public static void traTB(int maTB){
         thongtinsdDAL.traTB(maTB);
     }
}

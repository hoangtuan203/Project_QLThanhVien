/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.thongtinsd;
import DAL.xuly;
import DAL.xulyDAL;
import java.util.List;

/**
 *
 * @author PHY
 */
public class xulyBUS {
     public static List<xuly> selectAll()
     {
         return  xulyDAL.selectAll();
     }
      public static xuly selectxulybymaxl(int maxl) 
      {
          return  xulyDAL.selectxulybymaxl(maxl);
      }
      public static List<thongtinsd> selectalltv() {
          return xulyDAL.selectalltv();
      }
       public static boolean deleteXL(xuly t)
       {
           return xulyDAL.deleteXL(t);
       }
       public static boolean addXL(xuly t)
       {
           return xulyDAL.addXL(t);
       }
      public static boolean updateXL(xuly t)
       {
           return xulyDAL.updateXL(t);
       }
      public static List<xuly> search(String keyword)
     {
         return  xulyDAL.searchXL(keyword);
     }
          
          
}

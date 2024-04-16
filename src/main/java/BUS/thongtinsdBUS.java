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

    public static void addDevice(thongtinsd tb) {
        thongtinsdDAL.add(tb);
    }

    public static int getIdDevice() {
        return thongtinsdDAL.getNextDeviceID();
    }
    
    public static void deleteDevice(thongtinsd tb){
        thongtinsdDAL.delete(tb);
    }
    
    public static void updateDevice(thongtinsd tb){
        thongtinsdDAL.update(tb);
    }
    
    public static List<thongtinsd> getListByDeviceID(int deviceID){
        return thongtinsdDAL.ListByDeviceID(deviceID);
    }
    
    public static List<thongtinsd> getListByDeviceName(String deviceName){
        return thongtinsdDAL.ListByDeviceName(deviceName);
    }
    
    public static List<thongtinsd> getListByDeviceDescription(String deviceDescription){
        return thongtinsdDAL.ListByDeviceDescription(deviceDescription);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.thietbi;
import java.util.List;
import DAL.thietbiDAL;

/**
 *
 * @author Cong anh
 */
public class thietbiBUS {

    public static List<thietbi> getAllDevice() {
        return thietbiDAL.selectAll();
    }

    public static void addDevice(thietbi tb) {
        thietbiDAL.add(tb);
    }

    public static int getIdDevice() {
        return thietbiDAL.getNextDeviceID();
    }

    public static void deleteDevice(thietbi tb) {
        thietbiDAL.delete(tb);
    }

    public static void updateDevice(thietbi tb) {
        thietbiDAL.update(tb);
    }

    public static List<thietbi> getListByDeviceID(int deviceID) {
        return thietbiDAL.ListByDeviceID(deviceID);
    }

    public static List<thietbi> getListByDeviceName(String deviceName) {
        return thietbiDAL.ListByDeviceName(deviceName);
    }

    public static List<thietbi> getListByDeviceDescription(String deviceDescription) {
        return thietbiDAL.ListByDeviceDescription(deviceDescription);
    }
    
     public static List<thietbi> getDevicesByBorrowDateAndReturnDate() {
        return thietbiDAL.getDevicesByBorrowDateAndReturnDate();
    }
}

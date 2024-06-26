/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.thietbi;
import java.util.List;
import DAL.thietbiDAL;
import java.io.File;

/**
 *
 * @author Cong anh
 */
public class thietbiBUS {

    public static List<thietbi> getAllDevice() {
        return thietbiDAL.selectAll();
    }

    public static void addDevice(int deviceID, String name, String description) {
        thietbi tb = new thietbi(deviceID, name, description);

        thietbiDAL.add(tb);
    }

    public static void deleteDevice(int deviceID, String name, String description) {
        thietbi tb = new thietbi(deviceID, name, description);
        
        thietbiDAL.delete(tb);
    }

    public static void updateDevice(int deviceID, String name, String description) {
        thietbi tb = new thietbi(deviceID, name, description);
        
        thietbiDAL.update(tb);
    }

    public static int getIdDevice() {
        return thietbiDAL.getNextDeviceID();
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

    public static void importExcelToDatabase(File excelFile) {
        thietbiDAL.importExcelToDatabase(excelFile);
    }
    public static List<thietbi> getDevicesChuaDuocMuon() {
        return thietbiDAL.getDevicesChuaDuocMuon();
    }
}

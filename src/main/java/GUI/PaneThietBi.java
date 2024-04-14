/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.thietbiBUS;
import DAL.thietbi;
import DAL.thietbiDAL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class PaneThietBi extends javax.swing.JPanel {

    /**
     * Creates new form PaneThietBi
     */
    public PaneThietBi() {
        initComponents();
        displaytablethietbi();
    }

    public void displaytablethietbi() {
        List<thietbi> display = thietbiBUS.getAllDevice();
        DefaultTableModel model = (DefaultTableModel) jTableDevice.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (thietbi i : display) {
            Object[] row = {
                stt++,
                i.getMaTB(),
                i.getTenTB(),
                i.getMoTaTB(),};
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDevice = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new java.awt.Button();
        btnUpdate = new java.awt.Button();
        btnDelete = new java.awt.Button();
        jcbAll = new javax.swing.JCheckBox();
        jcbBorrowed = new javax.swing.JCheckBox();
        jcbEmpty = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        btnImportExcel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jtfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jtfName = new javax.swing.JTextField();
        jtfDescription = new javax.swing.JTextField();
        jcbStatus = new javax.swing.JComboBox<>();
        btnReload = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jPanel1.setPreferredSize(new java.awt.Dimension(1151, 733));

        jTableDevice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã thiết bị", "Tên thiết bị", "Mô tả", "Trạng thái"
            }
        ));
        jTableDevice.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableDevice.setShowGrid(true);
        jScrollPane1.setViewportView(jTableDevice);

        jLabel1.setText("Tên thiết bị :");

        jLabel2.setText("Trạng thái: ");

        btnAdd.setActionCommand("Thêm");
        btnAdd.setLabel("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setActionCommand("Sửa");
        btnUpdate.setLabel("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setActionCommand("Xóa");
        btnDelete.setLabel("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jcbAll.setText("All");
        jcbAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAllActionPerformed(evt);
            }
        });

        jcbBorrowed.setText("Đang được mượn");

        jcbEmpty.setText("Đang trống");

        jLabel3.setText("Mô tả: ");

        btnImportExcel.setText("Nhập Excel");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        jLabel4.setText("Tìm kiếm: ");

        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jcbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang được mượn", "Đang trống", " ", " " }));

        btnReload.setText("Tải lại");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel4)
                        .addGap(38, 38, 38)
                        .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jcbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(84, 84, 84)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(26, 26, 26)
                                        .addComponent(jtfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jcbAll)
                        .addGap(34, 34, 34)
                        .addComponent(jcbBorrowed)
                        .addGap(18, 18, 18)
                        .addComponent(jcbEmpty)))
                .addGap(119, 191, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jtfSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAll)
                    .addComponent(jcbBorrowed)
                    .addComponent(jcbEmpty))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jcbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String name = (String) jtfName.getText();
        String des = (String) jtfDescription.getText();
        int maTB = thietbiBUS.getIdDevice();
        thietbi tb = new thietbi(maTB, name, des);

        List<thietbi> allDevice = thietbiBUS.getAllDevice();

        if (!allDevice.contains(tb)) {
            thietbiBUS.addDevice(tb);
            JOptionPane.showMessageDialog(this, "Thêm thiết bị thành công");
            displaytablethietbi();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = jTableDevice.getSelectedRow();

        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int MaTB = Integer.parseInt(jTableDevice.getValueAt(selectedRow, 1).toString());
                String TenTB = jTableDevice.getValueAt(selectedRow, 2).toString();
                String MoTa = jTableDevice.getValueAt(selectedRow, 3).toString();

                thietbi tb = new thietbi(MaTB, TenTB, MoTa);
                thietbiBUS.deleteDevice(tb);
                JOptionPane.showMessageDialog(this, "Xóa thiết bị thành công");

                displaytablethietbi();

            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đối tượng xóa trên table");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = jTableDevice.getSelectedRow();
        if (selectedRow >= 0) {
            String name = jtfName.getText();
            String description = jtfDescription.getText();
            int selectedDeviceID = (int) jTableDevice.getValueAt(selectedRow, 1);

            thietbi updatedDevice = new thietbi(selectedDeviceID, name, description);

            thietbiBUS.updateDevice(updatedDevice);

            JOptionPane.showMessageDialog(this, "Sửa thiết bị thành công");
            displaytablethietbi();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thiết bị cần cập nhật");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String keyword = jtfSearch.getText().trim();

        if (keyword.isEmpty()) {
            displaytablethietbi();
        } else {
            List<thietbi> danhsachthietbi;

            try {
                int keywordInt = Integer.parseInt(keyword);
                danhsachthietbi = thietbiBUS.getListByDeviceID(keywordInt);
            } catch (NumberFormatException e) {
                danhsachthietbi = thietbiBUS.getListByDeviceName(keyword);
            }

            displaySearchResult(danhsachthietbi);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        displaytablethietbi();
        jtfName.setText(null);
        jtfDescription.setText(null);
        jtfSearch.setText(null);

    }//GEN-LAST:event_btnReloadActionPerformed

    private void jcbAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAllActionPerformed
        boolean selectAll = jcbAll.isSelected();

        jcbAll.setSelected(selectAll);

        if (selectAll) {
            for (int i = 0; i <= jTableDevice.getRowCount(); i++) {
                jTableDevice.changeSelection(i, 0, true, false);
            }
        } else {
            for (int i = 0; i <= jTableDevice.getRowCount(); i++) {
                jTableDevice.changeSelection(i, 0, false, false);
            }
        }


    }//GEN-LAST:event_jcbAllActionPerformed

    private void displaySearchResult(List<thietbi> thietBi) {
        DefaultTableModel model = (DefaultTableModel) jTableDevice.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (thietbi tb : thietBi) {
            Object[] row = {
                stt++,
                tb.getMaTB(),
                tb.getTenTB(),
                tb.getMoTaTB(),};
            model.addRow(row);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAdd;
    private java.awt.Button btnDelete;
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSearch;
    private java.awt.Button btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDevice;
    private javax.swing.JCheckBox jcbAll;
    private javax.swing.JCheckBox jcbBorrowed;
    private javax.swing.JCheckBox jcbEmpty;
    private javax.swing.JComboBox<String> jcbStatus;
    private javax.swing.JTextField jtfDescription;
    private javax.swing.JTextField jtfName;
    private javax.swing.JTextField jtfSearch;
    // End of variables declaration//GEN-END:variables
}

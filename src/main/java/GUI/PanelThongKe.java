/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ThongKeBUS;
import DAL.xuly;
import DAL.xulyDAL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author ASUS
 */
public class PanelThongKe extends javax.swing.JPanel {

    PaneThanhVien pane = new PaneThanhVien();
    ThongKeBUS thongKeBUS = new ThongKeBUS();

    /**
     * Creates new form PanelThongKe
     */
    public PanelThongKe() {
        initComponents();
        showPieChart();
        // showBarChart();
        showLineChart();
        displaytablexuly();
    }

    public void showPieChart() {

        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset.setValue("IPhone 5s", Double.valueOf(20));
        barDataset.setValue("SamSung Grand", Double.valueOf(20));
        barDataset.setValue("MotoG", Double.valueOf(40));
        barDataset.setValue("Nokia Lumia", Double.valueOf(10));

        JFreeChart piechart = ChartFactory.createPieChart("mobile sales", barDataset, false, true, false);// explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        piePlot.setSectionPaint("IPhone 5s", new Color(255, 255, 102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102, 255, 102));
        piePlot.setSectionPaint("MotoG", new Color(255, 102, 153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0, 204, 204));

        piePlot.setBackgroundPaint(Color.white);

        ChartPanel barChartPanel = new ChartPanel(piechart);
        pnXuLy.removeAll();
        pnXuLy.add(barChartPanel, BorderLayout.CENTER);
        pnXuLy.validate();
    }

    public void showLineChart() {
        // create dataset for the graph
        List<Date> thoiDiemList = thongKeBUS.getAllDateVao();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Integer> listQuantity = thongKeBUS.getNumberOfMembersForDate();

        // Duyệt qua danh sách ngày và số lượng tương ứng
        for (int i = 0; i < thoiDiemList.size(); i++) {
            Date date = thoiDiemList.get(i);
            Integer quantity = listQuantity.get(i);

            // Kiểm tra xem ngày và số lượng có null không
            if (date != null && quantity != null) {
                // Kiểm tra xem ngày hiện tại có trùng với ngày trước đó không
                if (i > 0 && thoiDiemList.get(i - 1) != null && thoiDiemList.get(i - 1).equals(date)) {
                    // Nếu trùng, tăng giá trị quantity lên 1
                    quantity++;
                }

                // Thêm giá trị vào dataset
                dataset.addValue(quantity, "Quantity", date);
            }
        }
        // create chart
        JFreeChart linechart = ChartFactory.createLineChart("Thống Kê Số Lượng Vào Khu Học Tập", "Thời Gian",
                "Số Lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        // create plot object
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);

        // create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        // create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        pnThanhVien.removeAll();
        pnThanhVien.add(lineChartPanel, BorderLayout.CENTER);
        pnThanhVien.validate();
    }

    /*
     * =============================================================================
     */
    public void showLineChart(Date dateStart, Date dateEnd) {
        // Tạo dataset mới để lưu trữ dữ liệu được lọc
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Lấy dữ liệu từ thongKeBUS dựa trên ngày bắt đầu và kết thúc
        List<Date> thoiDiemList = thongKeBUS.getFilteredDateVao(dateStart, dateEnd);
        List<Integer> listQuantity = thongKeBUS.getFilteredNumberOfMembersForDate(dateStart, dateEnd);

        // Duyệt qua danh sách ngày và số lượng tương ứng
        for (int i = 0; i < thoiDiemList.size(); i++) {
            Date date = thoiDiemList.get(i);
            Integer quantity = listQuantity.get(i);

            // Kiểm tra xem ngày và số lượng có null không
            if (date != null && quantity != null) {
                // Kiểm tra xem ngày hiện tại có trùng với ngày trước đó không
                if (i > 0 && thoiDiemList.get(i - 1) != null && thoiDiemList.get(i - 1).equals(date)) {
                    // Nếu trùng, tăng giá trị quantity lên 1
                    quantity++;
                }

                // Thêm giá trị vào dataset
                dataset.addValue(quantity, "Quantity", date);
            }
        }

        // Tạo biểu đồ
        JFreeChart linechart = ChartFactory.createLineChart("Thống Kê Số Lượng Vào Khu Học Tập", "Thời Gian",
                "Số Lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        // Cấu hình biểu đồ
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);

        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        // Tạo panel chứa biểu đồ
        ChartPanel lineChartPanel = new ChartPanel(linechart);

        // Hiển thị biểu đồ trên giao diện
        pnThanhVien.removeAll();
        pnThanhVien.add(lineChartPanel, BorderLayout.CENTER);
        pnThanhVien.validate();
    }

    /*
     * =============================================================================
     * ===========
     */
    public void showBarChart() {

        List<Date> thoiDiemList = thongKeBUS.getAllDateVao();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Integer> listQuantity = thongKeBUS.getNumberOfMembersForDate();

        // Duyệt qua danh sách ngày và số lượng tương ứng
        for (int i = 0; i < thoiDiemList.size(); i++) {
            Date date = thoiDiemList.get(i);
            int quantity = listQuantity.get(i);

            // Kiểm tra nếu ngày trước đó trùng với ngày hiện tại
            if (i > 0 && thoiDiemList.get(i - 1).equals(date)) {
                // Nếu trùng, tăng giá trị quantity lên 1
                quantity++;
            }

            // Thêm giá trị vào dataset
            dataset.addValue(quantity, "Quantity", date);
        }

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createBarChart("Thống Kê Thành Viên Vào Khu Học Tập", "Thời Gian", "Số Lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        // Tùy chỉnh màu sắc và nền của biểu đồ
        chart.setBackgroundPaint(Color.WHITE);

        // Tùy chỉnh màu sắc của cột
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        // Tạo panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);

        // Hiển thị biểu đồ trên giao diện
        pnThanhVien.removeAll();
        pnThanhVien.add(chartPanel, BorderLayout.CENTER);
        pnThanhVien.validate();
    }

    // load data tabel xuly
    public void displaytablexuly() {
        List<xuly> display = xulyDAL.selectAll();
        DefaultTableModel model = (DefaultTableModel) tableXuLy.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (xuly i : display) {
            Object[] row = {
                stt++,
                i.getMaXL(),
                i.getMaTV(),
                i.getHinhThucXL(),
                i.getSoTien(),
                i.getTrangThaiXL()
            };
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnThanhVien = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateStart = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dateEnd = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnFind = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnXuLy = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableXuLy = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1041, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 609, Short.MAX_VALUE));

        jTabbedPane1.addTab("Thiết Bị", jPanel2);

        pnThanhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnThanhVien.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Thời gian :");

        jLabel2.setText("đến");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Khoa:");

        jLabel4.setText("Ngành:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "SP KHXH", "Ngoại Ngữ", "Toán UD", "QLGD", "QTKD", "SP KHTN", "CNTT" }));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Địa", "Anh", "Sử", "NNA", "Toán", "TLH", "QTKD", "Lí", "Văn", "CNTT" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, 120, Short.MAX_VALUE)
                                .addContainerGap()));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)));

        btnFind.setText("Lọc");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(79, 79, 79)
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 102,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 104,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(56, 56, 56)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(222, 222, 222)
                                                .addComponent(pnThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(88, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(dateStart,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(dateEnd,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel2))
                                                                .addGap(133, 133, 133)
                                                                .addComponent(pnThanhVien,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel1)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(411, Short.MAX_VALUE)));

        jTabbedPane1.addTab("Thành Viên", jPanel1);

        pnXuLy.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnXuLy.setLayout(new java.awt.BorderLayout());

        tableXuLy.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null }
                },
                new String[] {
                        "STT", "Mã XL", "Mã TV", "Hình Thức XL", "Số Tiền Phạt", "Trạng thái XL"
                }));
        jScrollPane1.setViewportView(tableXuLy);

        jLabel5.setText("Tổng Tiền Phạt :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(pnXuLy, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel3Layout.createSequentialGroup()
                                                        .addComponent(jLabel5)
                                                        .addGap(77, 77, 77))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                .createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 193,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap()))));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnXuLy, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jTabbedPane1.addTab("Xử Lý", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFindActionPerformed
        Date dateStart = this.dateStart.getDate();
        Date dateEnd = this.dateEnd.getDate();
        if (dateStart == null || dateEnd == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc !");
        } else {
            // Gọi phương thức showLineChart() với tham số là ngày bắt đầu và kết thúc đã
            // chọn
            showLineChart(dateStart, dateEnd);
        }
    }// GEN-LAST:event_btnFindActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFind;
    private com.toedter.calendar.JDateChooser dateEnd;
    private com.toedter.calendar.JDateChooser dateStart;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel pnThanhVien;
    private javax.swing.JPanel pnXuLy;
    private javax.swing.JTable tableXuLy;
    // End of variables declaration//GEN-END:variables
}

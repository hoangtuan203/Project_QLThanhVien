/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ThongKeBUS;
import BUS.thietbiBUS;
import BUS.thongtinsdBUS;
import DAL.thietbi;
import DAL.thongtinsd;
import DAL.xuly;
import DAL.xulyDAL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
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
    private thietbiBUS tbbus = new thietbiBUS();
    private thongtinsdBUS ttsdbus = new thongtinsdBUS();
    private JSpinner dateSpinnerbd;
    private SpinnerDateModel spinnerDateModelbd;
    private JSpinner dateSpinnerkt;
    private SpinnerDateModel spinnerDateModelkt;

    /**
     * Creates new form PanelThongKe
     */
    public PanelThongKe() {
        initComponents();
        loadTotalAmount();
        spinnerDateModelbd = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        dateSpinnerbd = new JSpinner(spinnerDateModelbd);
        JSpinner.DateEditor dateEditorbd = new JSpinner.DateEditor(dateSpinnerbd, "dd/MM/yyyy HH:mm:ss");
        dateSpinnerbd.setEditor(dateEditorbd);

        spinnerDateModelkt = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        dateSpinnerkt = new JSpinner(spinnerDateModelkt);
        JSpinner.DateEditor dateEditorkt = new JSpinner.DateEditor(dateSpinnerkt, "dd/MM/yyyy HH:mm:ss");
        dateSpinnerkt.setEditor(dateEditorkt);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 110, Short.MAX_VALUE)
                        .addComponent(dateSpinnerbd)
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(dateSpinnerbd)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 128, Short.MAX_VALUE)
                        .addComponent(dateSpinnerkt)
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(dateSpinnerkt)
        );

        showPieChart();
        // showBarChart();
        showLineChart();
        displaytablexuly();
        showDevice();
    }

    public void showPieChart() {
        // Lấy danh sách trạng thái từ cơ sở dữ liệu
        List<Integer> statuses = thongKeBUS.getAllTrangThaiXL(); // Gọi phương thức đã được định nghĩa trước đó

        // Tạo dataset cho Pie Chart
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        // Tính tổng số lượng trạng thái
        int totalCount = statuses.size(); // Số lượng trạng thái là độ dài của danh sách

        // Thêm dữ liệu vào dataset và đặt nhãn cho từng phần tử
        for (Integer status : statuses) {
            int count = thongKeBUS.getCountForStatus(status); // Lấy số lượng dựa trên trạng thái, bạn cần tự triển khai

            // Đặt nhãn cho từng phần tử
            String label;
            switch (status) {
                case 0:
                    label = "Đang Xử Lý";
                    break;
                case 1:
                    label = "Đã Xử Lý";
                    break;
                default:
                    label = "Khác"; // Có thể thay đổi tùy theo nhu cầu của bạn
                    break;
            }

            // Tính phần trăm
            double percentage = ((double) count / (double) totalCount) * 100.0;

            // Thêm dữ liệu vào dataset
            pieDataset.setValue(label, percentage);
        }

        // Tạo Pie Chart
        JFreeChart pieChart = ChartFactory.createPieChart("Status Distribution", pieDataset, true, true, false);

        // Cài đặt hiển thị phần trăm trên các phần tử của biểu đồ
        PiePlot plot = (PiePlot) pieChart.getPlot();
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} ({2})");
        plot.setLegendLabelGenerator(labelGenerator);

        // Tạo Panel để hiển thị biểu đồ
        ChartPanel chartPanel = new ChartPanel(pieChart);

        // Thêm Panel vào frame hoặc container của bạn
        pnXuLy.removeAll();
        pnXuLy.add(chartPanel, BorderLayout.CENTER);
        pnXuLy.validate();
    }

    // tinh tong tien phat 
    public void loadTotalAmount() {
        // Gọi phương thức để lấy tổng số tiền
        int totalAmount = thongKeBUS.getTotalAmount();

        // Gán giá trị tổng số tiền vào TextField
        if (totalAmount == 0) {
            txtTongTienPhat.setText("0");
        } else {
            txtTongTienPhat.setText(String.valueOf(totalAmount));
        }
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
        JFreeChart chart = ChartFactory.createBarChart("Thành Viên Vào Khu Học Tập", "Thời Gian", "Số Lượng",
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

    public void showDevice() {
        List<thietbi> listtb = tbbus.getAllDevice();
        DefaultTableModel model = (DefaultTableModel) ThietBiTable.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (thietbi i : listtb) {
            Object[] row = {
                stt++,
                i.getMaTB(),
                i.getTenTB(),
                i.getMoTaTB(),};
            model.addRow(row);
        }
    }

    public void searchDevice(String tgbd, String tgkt, String tentb, int trangthai) {
        DefaultTableModel model = (DefaultTableModel) ThietBiTable.getModel();
        model.setColumnCount(0);
        model.addColumn("STT");
        model.addColumn("Mã TB");
        model.addColumn("Tên TB");
        model.addColumn("Mô Tả TB");
        model.addColumn("Ngày Vào");
        model.addColumn("Ngày Mượn");
        model.addColumn("Ngày Trả");

        List<thietbi> listtb = tbbus.getAllDevice();
        List<thongtinsd> listttsd = ttsdbus.getAllDevice();
        String datebd = tgbd.split(" ")[0];
        String yearbd = datebd.split("-")[0];
        String monthbd = datebd.split("-")[1];
        String daybd = datebd.split("-")[2];
        
        String timebd = tgbd.split(" ")[1];
        String hourbd = timebd.split(":")[0];
        String minbd = timebd.split(":")[1];
        String secbd = timebd.split(":")[2];
        
        String datekt = tgkt.split(" ")[0];
        String yearkt = datekt.split("-")[0];
        String monthkt = datekt.split("-")[1];
        String daykt = datekt.split("-")[2];
        
        String timekt = tgkt.split(" ")[1];
        String hourkt = timekt.split(":")[0];
        String minkt = timekt.split(":")[1];
        String seckt = timekt.split(":")[2];
        model.setRowCount(0);
        int stt = 1;
        for (thietbi i : listtb) {
            int s = 0;
            for (thongtinsd j : listttsd) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String valuebd = sdf.format(j.getTgMuon());
                String valuekt = sdf.format(j.getTgTra());

                String datebdv = valuebd.split(" ")[0];
                String yearbdv = datebdv.split("-")[0];
                String monthbdv = datebdv.split("-")[1];
                String daybdv = datebdv.split("-")[2];

                String timebdv = valuebd.split(" ")[1];
                String hourbdv = timebdv.split(":")[0];
                String minbdv = timebdv.split(":")[1];
                String secbdv = timebdv.split(":")[2];

                String datektv = valuekt.split(" ")[0];
                String yearktv = datektv.split("-")[0];
                String monthktv = datektv.split("-")[1];
                String dayktv = datektv.split("-")[2];

                String timektv = valuekt.split(" ")[1];
                String hourktv = timektv.split(":")[0];
                String minktv = timektv.split(":")[1];
                String secktv = timektv.split(":")[2];
                if(trangthai == 0) {
                    int l = 0;
                    if (i.getTenTB().equalsIgnoreCase(tentb) && j.getMaTB() == i.getMaTB()) {
                        s++;
                        if(Integer.parseInt(yearbd) > Integer.parseInt(yearktv)) {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearktv) 
                                && Integer.parseInt(monthbd) > Integer.parseInt(monthktv))  {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearktv) 
                                && Integer.parseInt(monthbd) == Integer.parseInt(monthktv) 
                                && Integer.parseInt(daybd) > Integer.parseInt(dayktv))  {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearktv) 
                                && Integer.parseInt(monthbd) == Integer.parseInt(monthktv) 
                                && Integer.parseInt(daybd) == Integer.parseInt(dayktv)
                                && Integer.parseInt(hourbd) > Integer.parseInt(hourktv))  {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearktv) 
                                && Integer.parseInt(monthbd) == Integer.parseInt(monthktv) 
                                && Integer.parseInt(daybd) == Integer.parseInt(dayktv)
                                && Integer.parseInt(hourbd) == Integer.parseInt(hourktv)
                                && Integer.parseInt(minbd) > Integer.parseInt(minktv))  {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearktv) 
                                && Integer.parseInt(monthbd) == Integer.parseInt(monthktv) 
                                && Integer.parseInt(daybd) == Integer.parseInt(dayktv)
                                && Integer.parseInt(hourbd) == Integer.parseInt(hourktv)
                                && Integer.parseInt(minbd) == Integer.parseInt(minktv)
                                && Integer.parseInt(secbd) > Integer.parseInt(secktv))  {
                            l = 1;
                        }
                    }
                    
                    if(l == 1) {
                        if(model.getRowCount() != 0) {
                            System.out.println(stt--);
                            model.removeRow(stt - 1);
                        }
                        Object[] row = {
                            stt++,
                            i.getMaTB(),
                            i.getTenTB(),
                            i.getMoTaTB(),
                            j.getTgVao(),
                            j.getTgMuon(),
                            j.getTgTra()
                        };
                        model.addRow(row);
                    }
                }
                else if (trangthai == 1){
                    int l = 0;
                    s++;
                    if (i.getTenTB().equalsIgnoreCase(tentb) && j.getMaTB() == i.getMaTB()) {
                        if(Integer.parseInt(yearbd) < Integer.parseInt(yearbdv) 
                            && Integer.parseInt(yearkt) >= Integer.parseInt(yearktv)) {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearbdv) 
                            && Integer.parseInt(yearkt) == Integer.parseInt(yearktv)
                            && Integer.parseInt(monthbd) < Integer.parseInt(monthbdv) 
                            && Integer.parseInt(monthkt) >= Integer.parseInt(monthktv)) {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearbdv) 
                            && Integer.parseInt(yearkt) == Integer.parseInt(yearktv)
                            && Integer.parseInt(monthbd) == Integer.parseInt(monthbdv) 
                            && Integer.parseInt(monthkt) == Integer.parseInt(monthktv)
                            && Integer.parseInt(daybd) < Integer.parseInt(daybdv) 
                            && Integer.parseInt(daykt) >= Integer.parseInt(dayktv)) {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearbdv) 
                            && Integer.parseInt(yearkt) == Integer.parseInt(yearktv)
                            && Integer.parseInt(monthbd) == Integer.parseInt(monthbdv) 
                            && Integer.parseInt(monthkt) == Integer.parseInt(monthktv)
                            && Integer.parseInt(daybd) == Integer.parseInt(daybdv) 
                            && Integer.parseInt(daykt) == Integer.parseInt(dayktv)
                            && Integer.parseInt(hourbd) < Integer.parseInt(hourbdv) 
                            && Integer.parseInt(hourkt) >= Integer.parseInt(hourktv)) {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearbdv) 
                            && Integer.parseInt(yearkt) == Integer.parseInt(yearktv)
                            && Integer.parseInt(monthbd) == Integer.parseInt(monthbdv) 
                            && Integer.parseInt(monthkt) == Integer.parseInt(monthktv)
                            && Integer.parseInt(daybd) == Integer.parseInt(daybdv) 
                            && Integer.parseInt(daykt) == Integer.parseInt(dayktv)
                            && Integer.parseInt(hourbd) == Integer.parseInt(hourbdv) 
                            && Integer.parseInt(hourkt) == Integer.parseInt(hourktv)
                            && Integer.parseInt(minbd) < Integer.parseInt(minbdv) 
                            && Integer.parseInt(minkt) >= Integer.parseInt(minktv)) {
                            l = 1;
                        } else if(Integer.parseInt(yearbd) == Integer.parseInt(yearbdv) 
                            && Integer.parseInt(yearkt) == Integer.parseInt(yearktv)
                            && Integer.parseInt(monthbd) == Integer.parseInt(monthbdv) 
                            && Integer.parseInt(monthkt) == Integer.parseInt(monthktv)
                            && Integer.parseInt(daybd) == Integer.parseInt(daybdv) 
                            && Integer.parseInt(daykt) == Integer.parseInt(dayktv)
                            && Integer.parseInt(hourbd) == Integer.parseInt(hourbdv) 
                            && Integer.parseInt(hourkt) == Integer.parseInt(hourktv)
                            && Integer.parseInt(minbd) == Integer.parseInt(minbdv) 
                            && Integer.parseInt(minkt) == Integer.parseInt(minktv)
                            && Integer.parseInt(secbd) < Integer.parseInt(secbdv) 
                            && Integer.parseInt(seckt) >= Integer.parseInt(secktv)) {
                            l = 1;
                        }
                    }
                    
                    if(l == 1) {
                        if(model.getRowCount() != 0) {
                            System.out.println(stt--);
                            model.removeRow(stt - 1);
                        }
                        Object[] row = {
                            stt++,
                            i.getMaTB(),
                            i.getTenTB(),
                            i.getMoTaTB(),
                            j.getTgVao(),
                            j.getTgMuon(),
                            j.getTgTra()
                        };
                        model.addRow(row);
                    }
                }
            }
            
            if(i.getTenTB().equalsIgnoreCase(tentb) && s == 0) {
                Object[] row = {
                    stt++,
                    i.getMaTB(),
                    i.getTenTB(),
                    i.getMoTaTB(),
                    "chua co",
                    "chua co",
                    "chua co"
                };
                model.addRow(row);
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        pnThanhVien1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tentbcbb = new javax.swing.JComboBox<>();
        btnFind1 = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ThietBiTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
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
        btnSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnXuLy = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableXuLy = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTongTienPhat = new javax.swing.JTextField();

        pnThanhVien1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnThanhVien1.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Thời gian :");

        jLabel7.setText("đến :");
        jLabel7.setToolTipText("");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setText("Tên:");

        tentbcbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Micro", "Bảng điện tử" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tentbcbb, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(13, 13, 13))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tentbcbb)
                .addGap(7, 7, 7))
        );

        btnFind1.setText("Lọc");
        btnFind1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFind1ActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "được mượn theo thời gian", "đang được mượn theo thời gian" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        ThietBiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã TB", "Tên TB", "Mô Tả TB"
            }
        ));
        jScrollPane2.setViewportView(ThietBiTable);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnFind1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(pnThanhVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFind1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(pnThanhVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                        .addGap(14, 14, 14))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thiết Bị", jPanel2);

        pnThanhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnThanhVien.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Thời gian :");

        jLabel2.setText("đến");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Khoa:");

        jLabel4.setText("Ngành:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP KHXH", "Ngoại Ngữ", "Toán UD", "QLGD", "QTKD", "SP KHTN", "CNTT" }));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Địa", "Anh", "Sử", "NNA", "Toán", "TLH", "QTKD", "Lí", "Văn", "CNTT" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        btnSearch.setText("Lọc");

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
                        .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(pnThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(90, 90, 90)
                                .addComponent(pnThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(500, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thành Viên", jPanel1);

        pnXuLy.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnXuLy.setLayout(new java.awt.BorderLayout());

        tableXuLy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã XL", "Mã TV", "Hình Thức XL", "Số Tiền Phạt", "Trạng thái XL"
            }
        ));
        jScrollPane1.setViewportView(tableXuLy);

        jLabel5.setText("Tổng Tiền Phạt :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(pnXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTongTienPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTienPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(305, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Xử Lý", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void btnFind1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFind1ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datebd = sdf.format(dateSpinnerbd.getValue());
        String datekt = sdf.format(dateSpinnerkt.getValue());
        searchDevice(datebd, datekt, (String) tentbcbb.getSelectedItem(), jComboBox5.getSelectedIndex());
    }//GEN-LAST:event_btnFind1ActionPerformed

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
    private javax.swing.JTable ThietBiTable;
    private javax.swing.JButton btnFind1;
    private javax.swing.JButton btnSearch;
    private com.toedter.calendar.JDateChooser dateEnd;
    private com.toedter.calendar.JDateChooser dateStart;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnThanhVien;
    private javax.swing.JPanel pnThanhVien1;
    private javax.swing.JPanel pnXuLy;
    private javax.swing.JTable tableXuLy;
    private javax.swing.JComboBox<String> tentbcbb;
    private javax.swing.JTextField txtTongTienPhat;
    // End of variables declaration//GEN-END:variables
}

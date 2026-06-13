package views;


import controllers.PhanCongController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class ThongKeForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> cboSuKien;
    private JTable table;
    private DefaultTableModel model;
    private PhanCongController controller = new PhanCongController();
    private JButton btnQuayLai;

    public ThongKeForm() {
        setTitle("Tra Cứu Danh Sách Tình Nguyện Viên Theo Sự Kiện");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initUI();

        if (!java.beans.Beans.isDesignTime()) {
            loadSuKienToComboBox();
        }
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelTop.setBorder(BorderFactory.createTitledBorder(" BỘ LỌC SỰ KIỆN "));
        
        panelTop.add(new JLabel("Chọn Sự kiện cần xem:"));
        cboSuKien = new JComboBox<>();
        cboSuKien.setPreferredSize(new Dimension(300, 30));
        panelTop.add(cboSuKien);
        
        btnQuayLai = new JButton("Quay lại Menu");
        btnQuayLai.setBackground(new Color(220, 225, 230));
        panelTop.add(btnQuayLai);

        add(panelTop, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Mã TV", "Họ và Tên", "Số Điện Thoại", "Vai Trò Được Phân Công"}, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" DANH SÁCH TÌNH NGUYỆN VIÊN THAM GIA "));
        
        add(scrollPane, BorderLayout.CENTER);

        btnQuayLai.addActionListener(e -> {
            new TrangChu().setVisible(true); 
            dispose(); 
        });
        cboSuKien.addActionListener(e -> {
            if (cboSuKien.getSelectedItem() != null) {
                String selectedSK = cboSuKien.getSelectedItem().toString();
                String maSK = selectedSK.split(" - ")[0];
                loadDanhSachTinhNguyenVien(maSK);
            }
        });
    }

    private void loadSuKienToComboBox() {
        cboSuKien.removeAllItems();
        List<String> listSK = controller.getDanhSachSuKien();
        for (String sk : listSK) {
            cboSuKien.addItem(sk);
        }
    }

    private void loadDanhSachTinhNguyenVien(String maSK) {
        model.setRowCount(0);
        List<models.ThanhVien> list = controller.getThanhVienTheoSuKien(maSK);
        for (models.ThanhVien tv : list) {
            model.addRow(new Object[]{
                tv.getMaTV(),
                tv.getTenTV(),
                tv.getSoDienThoai(),
                tv.getNhomMau() 
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThongKeForm().setVisible(true));
    }
}
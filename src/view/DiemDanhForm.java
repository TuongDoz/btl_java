package view;

import utils.DatabaseConection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DiemDanhForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> cboSuKien;
    private JComboBox<String> cboThanhVien;
    private JTextField txtVaiTro;
    private JButton btnLuu;

    private JTable table;
    private DefaultTableModel model;

    public DiemDanhForm() {
        setTitle("Phân Công / Điểm Danh");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();

        loadSuKien();
        loadThanhVien();
        loadDiemDanh();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new GridLayout(4, 2, 10, 10));

        panelTop.add(new JLabel("Sự kiện"));
        cboSuKien = new JComboBox<>();
        panelTop.add(cboSuKien);

        panelTop.add(new JLabel("Tình nguyện viên"));
        cboThanhVien = new JComboBox<>();
        panelTop.add(cboThanhVien);

        panelTop.add(new JLabel("Vai trò"));
        txtVaiTro = new JTextField();
        panelTop.add(txtVaiTro);

        panelTop.add(new JLabel(""));

        btnLuu = new JButton("Phân công / Điểm danh");
        panelTop.add(btnLuu);

        add(panelTop, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Mã SK", "Mã TV", "Vai Trò"},
                0);

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        btnLuu.addActionListener(e -> luuDiemDanh());
    }
    private void loadSuKien() {

        try {
            Connection conn = DatabaseConection.getConnection();

            String sql = "SELECT MaSK FROM SuKien";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                cboSuKien.addItem(rs.getString("MaSK"));
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadThanhVien() {

        try {
            Connection conn = DatabaseConection.getConnection();

            String sql = "SELECT MaTV FROM ThanhVien";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                cboThanhVien.addItem(rs.getString("MaTV"));
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDiemDanh() {

        model.setRowCount(0);

        try {
            Connection conn = DatabaseConection.getConnection();

            String sql =
                    "SELECT MaSK, MaTV, VaiTro FROM DiemDanh";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                model.addRow(new Object[] {
                        rs.getString("MaSK"),
                        rs.getString("MaTV"),
                        rs.getString("VaiTro")
                });
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void luuDiemDanh() {

        String maSK =
                cboSuKien.getSelectedItem().toString();

        String maTV =
                cboThanhVien.getSelectedItem().toString();

        String vaiTro =
                txtVaiTro.getText().trim();

        if(vaiTro.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nhập vai trò");

            return;
        }

        try {

            Connection conn =
                    DatabaseConection.getConnection();

            String sql =
                    "INSERT INTO DiemDanh(MaSK,MaTV,VaiTro) VALUES(?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, maSK);
            ps.setString(2, maTV);
            ps.setString(3, vaiTro);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Lưu thành công");

            loadDiemDanh();

            txtVaiTro.setText("");

            conn.close();

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Lỗi: " + e.getMessage());
        }
    }    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DiemDanhForm().setVisible(true);
        });
    }
}
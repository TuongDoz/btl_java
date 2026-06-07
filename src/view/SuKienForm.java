package view;

import utils.DatabaseConection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class SuKienForm extends JFrame {

    private JTextField txtMaSK, txtTenSK, txtNgayToChuc, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    private JTable table;
    private DefaultTableModel model;

    private final Color colorBg = new Color(245, 246, 250);     
    private final Color colorThem = new Color(168, 230, 207);   
    private final Color colorSua = new Color(168, 218, 220);    
    private final Color colorXoa = new Color(255, 138, 138);    
    private final Color colorLamMoi = new Color(220, 225, 230); 

    public SuKienForm() {
        setTitle("Quản Lý Sự Kiện");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(colorBg); 
        setLayout(new BorderLayout(15, 15)); 

        initUI();
        addEvents();
        loadData("");
    }

    private void initUI() {
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontInput = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel panelTimKiem = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10)); 
        panelTimKiem.setBackground(Color.WHITE);
        panelTimKiem.setBorder(BorderFactory.createTitledBorder(" TÌM KIẾM SỰ KIỆN "));
        
        JLabel lblTimKiem = new JLabel("Nhập từ khóa cần tìm:");
        lblTimKiem.setFont(fontLabel);
        txtTimKiem = new JTextField(40); 
        txtTimKiem.setFont(fontInput);

        panelTimKiem.add(lblTimKiem);
        panelTimKiem.add(txtTimKiem);

        JPanel northWrapper = new JPanel(new BorderLayout());
        northWrapper.setBackground(colorBg);
        northWrapper.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
        northWrapper.add(panelTimKiem, BorderLayout.CENTER);
        add(northWrapper, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(4, 1, 5, 5)); 
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder(" THÔNG TIN "));

        txtMaSK = new JTextField();     txtMaSK.setFont(fontInput);
        txtTenSK = new JTextField();    txtTenSK.setFont(fontInput);
        txtNgayToChuc = new JTextField(); txtNgayToChuc.setFont(fontInput);

        JPanel p1 = new JPanel(new BorderLayout(5, 5)); p1.setBackground(Color.WHITE);
        JLabel l1 = new JLabel("Mã sự kiện:"); l1.setFont(fontLabel); l1.setPreferredSize(new Dimension(100, 0));
        p1.add(l1, BorderLayout.WEST); p1.add(txtMaSK, BorderLayout.CENTER);
        panelForm.add(p1);

        JPanel p2 = new JPanel(new BorderLayout(5, 5)); p2.setBackground(Color.WHITE);
        JLabel l2 = new JLabel("Tên sự kiện:"); l2.setFont(fontLabel); l2.setPreferredSize(new Dimension(100, 0));
        p2.add(l2, BorderLayout.WEST); p2.add(txtTenSK, BorderLayout.CENTER);
        panelForm.add(p2);

        JPanel p3 = new JPanel(new BorderLayout(5, 5)); p3.setBackground(Color.WHITE);
        JLabel l3 = new JLabel("Ngày tổ chức:"); l3.setFont(fontLabel); l3.setPreferredSize(new Dimension(100, 0));
        p3.add(l3, BorderLayout.WEST); p3.add(txtNgayToChuc, BorderLayout.CENTER);
        panelForm.add(p3);

        JLabel lblNote = new JLabel("(Định dạng: yyyy-mm-dd) ", SwingConstants.RIGHT);
        lblNote.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblNote.setForeground(Color.GRAY);
        panelForm.add(lblNote);

        JPanel westWrapper = new JPanel(new BorderLayout());
        westWrapper.setBackground(colorBg);
        westWrapper.setPreferredSize(new Dimension(360, 0));
        westWrapper.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 0));
        westWrapper.add(panelForm, BorderLayout.NORTH);
        add(westWrapper, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Mã SK", "Tên Sự Kiện", "Ngày Tổ Chức"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } 
        };
        table = new JTable(model);
        table.setFont(fontInput);
        table.setRowHeight(30); 
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.BLACK);         
        headerRenderer.setForeground(Color.WHITE);         
        headerRenderer.setHorizontalAlignment(JLabel.CENTER); 
        
        table.getTableHeader().setFont(fontLabel);
        table.getTableHeader().setDefaultRenderer(headerRenderer); 

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" DANH SÁCH SỰ KIỆN "));

        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelNut.setBackground(colorBg);

        btnThem = createStyledButton("Thêm", colorThem);
        btnSua = createStyledButton("Sửa", colorSua);
        btnXoa = createStyledButton("Xóa", colorXoa);
        btnLamMoi = createStyledButton("Làm mới", colorLamMoi);
        
        panelNut.add(btnThem);
        panelNut.add(btnSua);
        panelNut.add(btnXoa);
        panelNut.add(btnLamMoi);

        JPanel panelCenter = new JPanel(new BorderLayout(0, 10));
        panelCenter.setBackground(colorBg);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        panelCenter.add(scrollPane, BorderLayout.CENTER);
        panelCenter.add(panelNut, BorderLayout.SOUTH);
        add(panelCenter, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color baseColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(110, 38));
        btn.setBackground(baseColor);
        btn.setForeground(Color.BLACK); 
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        return btn;
    }

    private void addEvents() {
        btnThem.addActionListener(this::themSuKien);
        btnSua.addActionListener(this::suaSuKien);
        btnXoa.addActionListener(this::xoaSuKien);
        btnLamMoi.addActionListener(e -> {
            clearForm();
            txtTimKiem.setText("");
            loadData("");
        });

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                loadData(txtTimKiem.getText().trim());
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                int row = table.getSelectedRow();
                txtMaSK.setText(model.getValueAt(row, 0).toString());
                txtTenSK.setText(model.getValueAt(row, 1).toString());
                Object dateVal = model.getValueAt(row, 2);
                txtNgayToChuc.setText(dateVal != null ? dateVal.toString() : "");
                txtMaSK.setEditable(false); 
            }
        });
    }
    
    private void loadData(String keyword) {
        model.setRowCount(0);
        String sql;
        
        if (keyword.isEmpty()) {
            sql = "SELECT MaSK, TenSK, NgayToChuc FROM SuKien ORDER BY NgayToChuc DESC";
        } else {
            sql = "SELECT MaSK, TenSK, NgayToChuc FROM SuKien WHERE MaSK LIKE ? OR TenSK LIKE ? ORDER BY NgayToChuc DESC";
        }

        try (Connection conn = DatabaseConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (!keyword.isEmpty()) {
                String param = "%" + keyword + "%";
                pstmt.setString(1, param);
                pstmt.setString(2, param);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("MaSK"), rs.getString("TenSK"), rs.getString("NgayToChuc")
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void themSuKien(ActionEvent e) {
        String ma = txtMaSK.getText().trim();
        String ten = txtTenSK.getText().trim();
        String ngay = txtNgayToChuc.getText().trim();

        if (ma.isEmpty() || ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Mã và Tên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO SuKien VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ma);
            pstmt.setString(2, ten);
            if (ngay.isEmpty()) pstmt.setNull(3, Types.DATE);
            else pstmt.setString(3, ngay);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm sự kiện thành công!");
            loadData("");
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suaSuKien(ActionEvent e) {
        String ma = txtMaSK.getText().trim();
        String ten = txtTenSK.getText().trim();
        String ngay = txtNgayToChuc.getText().trim();

        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sự kiện để sửa!");
            return;
        }

        String sql = "UPDATE SuKien SET TenSK = ?, NgayToChuc = ? WHERE MaSK = ?";
        try (Connection conn = DatabaseConection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ten);
            if (ngay.isEmpty()) pstmt.setNull(2, Types.DATE);
            else pstmt.setString(2, ngay);
            pstmt.setString(3, ma);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            loadData("");
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi sửa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoaSuKien(ActionEvent e) {
        String ma = txtMaSK.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sự kiện cần xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM SuKien WHERE MaSK = ?";
            try (Connection conn = DatabaseConection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, ma);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData("");
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi xóa: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearForm() {
        txtMaSK.setText("");
        txtTenSK.setText("");
        txtNgayToChuc.setText("");
        txtMaSK.setEditable(true);
        table.clearSelection();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new SuKienForm().setVisible(true));
    }
}
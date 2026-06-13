package views;

import controllers.PhanCongController;
import models.PhanCong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PhanCongForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> cboSuKien;
    private JComboBox<String> cboThanhVien;
    private JTextField txtVaiTro;
    private JButton btnLuu;
    private JButton btnQuayLai;

    private JTable table;
    private DefaultTableModel model;
    
    private PhanCongController controller = new PhanCongController();

    public PhanCongForm() {
        setTitle("Phân Công ");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();

        if (!java.beans.Beans.isDesignTime()) {
            loadSuKien();
            loadThanhVien();
            loadDiemDanh();
        }
    }

    private void initUI() {
        getContentPane().setLayout(new BorderLayout());

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Chọn Sự kiện:"));
        cboSuKien = new JComboBox<>();
        cboSuKien.setPreferredSize(new Dimension(250, 25));
        row1.add(cboSuKien);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Chọn Tình nguyện viên:"));
        cboThanhVien = new JComboBox<>();
        cboThanhVien.setPreferredSize(new Dimension(250, 25));
        row2.add(cboThanhVien);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("Vai trò:"));
        txtVaiTro = new JTextField(20);
        row3.add(txtVaiTro);

        JPanel row4 = new JPanel();
        btnLuu = new JButton("Phân công");
        btnQuayLai = new JButton("Quay lại Menu");
        row4.add(btnLuu);
        row4.add(btnQuayLai);

        panelTop.add(row1);
        panelTop.add(row2);
        panelTop.add(row3);
        panelTop.add(row4);

        getContentPane().add(panelTop, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Mã SK", "Mã TV", "Vai Trò"}, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        btnLuu.addActionListener(e -> luuDiemDanh());
        btnQuayLai.addActionListener(e -> {
            new TrangChu().setVisible(true); 
            dispose(); 
        });
    }

    private void loadSuKien() {
        cboSuKien.removeAllItems();
        List<String> listSK = controller.getDanhSachSuKien();
        for (String sk : listSK) {
            cboSuKien.addItem(sk);
        }
    }

    private void loadThanhVien() {
        cboThanhVien.removeAllItems();
        List<String> listTV = controller.getDanhSachThanhVien();
        if (listTV.isEmpty()) {
            cboThanhVien.addItem("-- Tất cả thành viên đều đã có sự kiện --");
        } else {
            for (String tv : listTV) {
                cboThanhVien.addItem(tv);
            }
        }
    }

    private void loadDiemDanh() {
        model.setRowCount(0);
        List<PhanCong> list = controller.getAllDiemDanh();
        for (PhanCong dd : list) {
            model.addRow(new Object[] {
                dd.getMaSK(),
                dd.getMaTV(),
                dd.getVaiTro()
            });
        }
    }

    private void luuDiemDanh() {
        if (cboSuKien.getItemCount() == 0 || cboThanhVien.getItemCount() == 0 || cboThanhVien.getSelectedItem().toString().startsWith("--")) {
            JOptionPane.showMessageDialog(this, "Không có đủ dữ liệu để phân công!");
            return;
        }


        String selectedSK = cboSuKien.getSelectedItem().toString();
        String maSK = selectedSK.split(" - ")[0];

        String selectedTV = cboThanhVien.getSelectedItem().toString();
        String maTV = selectedTV.split(" - ")[0];

        String vaiTro = txtVaiTro.getText().trim();

        if(vaiTro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vai trò!");
            txtVaiTro.requestFocus();
            return;
        }

        PhanCong dd = new PhanCong(maSK, maTV, vaiTro);

        if (controller.themDiemDanh(dd)) {
            JOptionPane.showMessageDialog(this, "Lưu phân công thành công!");
            loadDiemDanh(); 
            loadThanhVien(); 
            txtVaiTro.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Lưu thất bại!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PhanCongForm().setVisible(true);
        });
    }
}
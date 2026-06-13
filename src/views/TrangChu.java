package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TrangChu extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TrangChu frame = new TrangChu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TrangChu() {
        setTitle("Hệ Thống Quản Lý Tình Nguyện Viên - Trang Chủ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400); 
        setLocationRelativeTo(null); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblTieuDe = new JLabel(" QUẢN LÝ TÌNH NGUYỆN", SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTieuDe.setBounds(10, 30, 560, 40);
        contentPane.add(lblTieuDe);


        JButton btnThanhVien = new JButton("Quản Lý Thành Viên");
        btnThanhVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnThanhVien.setBounds(10, 109, 179, 32);
        contentPane.add(btnThanhVien);

  
        JButton btnSuKien = new JButton("Quản Lý Sự Kiện");
        btnSuKien.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                 SuKienForm skForm = new SuKienForm();
                 skForm.setVisible(true);
        	}
        });
        btnSuKien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSuKien.setBounds(10, 238, 179, 32);
        contentPane.add(btnSuKien);
        
        JButton btnTraCuu = new JButton("Thống kê sự kiện");
        btnTraCuu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ThongKeForm tkForm = new ThongKeForm();
                tkForm.setVisible(true);
        	}
        });
        btnTraCuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnTraCuu.setBounds(10, 195, 179, 32); 
        contentPane.add(btnTraCuu);

        
        JButton btnDiemDanh = new JButton("Phân Công Sự Kiện");
        btnDiemDanh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                 PhanCongForm ddForm = new PhanCongForm();
                 ddForm.setVisible(true);
        	}
        });
        btnDiemDanh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDiemDanh.setBounds(10, 152, 179, 32);
        contentPane.add(btnDiemDanh);


        JButton btnDangXuat = new JButton("Đăng Xuất");
        btnDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDangXuat.setBounds(47, 292, 110, 60);
        contentPane.add(btnDangXuat);


        

        btnDangXuat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int chon = JOptionPane.showConfirmDialog(contentPane, "Bạn có chắc muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    DangNhapForm login = new DangNhapForm();
                    login.setVisible(true);
                    dispose(); 
                }
            }
        });


        btnThanhVien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ThanhVienForm tvForm = new ThanhVienForm();
                tvForm.setVisible(true);
            }
        });
        

    }
}
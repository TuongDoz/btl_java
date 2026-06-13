package views;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import utils.DatabaseConnection;
import javax.swing.ImageIcon;



	

public class DangNhapForm extends JFrame {

	    private JPanel contentPane;
	    private JTextField txtTaiKhoan;
	    private JPasswordField txtMatKhau;

	  
	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    DangNhapForm frame = new DangNhapForm();
	                    frame.setVisible(true); 
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	    // Cấu hình giao diện Form
	    public DangNhapForm() {
	        setTitle("Đăng Nhập Hệ Thống");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 400, 250);
	        setLocationRelativeTo(null); 
	        
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JLabel lblTieuDe = new JLabel("QUẢN LÝ TÌNH NGUYỆN VIÊN");
	        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 16));
	        lblTieuDe.setBounds(70, 20, 250, 30);
	        contentPane.add(lblTieuDe);

	        JLabel lblTaiKhoan = new JLabel("Tài khoản:");
	        lblTaiKhoan.setBounds(40, 70, 80, 25);
	        contentPane.add(lblTaiKhoan);

	        txtTaiKhoan = new JTextField();
	        txtTaiKhoan.setBounds(130, 70, 200, 25);
	        contentPane.add(txtTaiKhoan);
	        txtTaiKhoan.setColumns(10);

	        JLabel lblMatKhau = new JLabel("Mật khẩu:");
	        lblMatKhau.setBounds(40, 110, 80, 25);
	        contentPane.add(lblMatKhau);

	        txtMatKhau = new JPasswordField();
	        txtMatKhau.setBounds(130, 110, 200, 25);
	        contentPane.add(txtMatKhau);

	        JButton btnDangNhap = new JButton("Đăng nhập");
	        btnDangNhap.setBounds(130, 160, 100, 30);
	        contentPane.add(btnDangNhap);
	        
	        JButton btnThoat = new JButton("Thoát");
	        btnThoat.setBounds(240, 160, 90, 30);
	        contentPane.add(btnThoat);
	        
	        JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon(DangNhapForm.class.getResource("/images/pngtree-blood-donor-logo-volunteer-drip-ribbon-vector-picture-image_10041972.png")));
	        lblNewLabel.setBounds(-28, 11, 100, 63);
	        contentPane.add(lblNewLabel);


	        btnThoat.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0); 
	            }
	        });

	        btnDangNhap.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String taikhoan = txtTaiKhoan.getText();
	                String matkhau = new String(txtMatKhau.getPassword());

	                if (taikhoan.isEmpty() || matkhau.isEmpty()) {
	                    JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập đủ Tài khoản và Mật khẩu!");
	                    return;
	                }
	                controllers.DangNhapConTroller dn = new controllers.DangNhapConTroller();
	                        if (dn.Ktra(taikhoan, matkhau)) {
	                           
	                       
	                             TrangChu main = new TrangChu();
	                             
	                            main.setVisible(true);
	                             dispose();
	                        } else {
	                            JOptionPane.showMessageDialog(contentPane, "Sai tài khoản hoặc mật khẩu!");
	                        }
	                    }
	               
	            
	        });
	    }
	}

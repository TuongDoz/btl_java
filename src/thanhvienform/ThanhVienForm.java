package thanhvienform;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.util.*;

public class ThanhVienForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblThanhVien;
	private JTextField txtMaTV;
	private JTextField txtTenTV;
	private JTextField txtSDT;
	 
	 


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThanhVienForm frame = new ThanhVienForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void loadData() {
	    DefaultTableModel model =
	            (DefaultTableModel) tblThanhVien.getModel();

	    model.setRowCount(0);

	    try {
	        Connection con = DAO.getConnection();

	        String sql = "SELECT * FROM ThanhVien";

	        PreparedStatement pst = con.prepareStatement(sql);

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {

	            model.addRow(new Object[]{
	                rs.getString("MaTV"),
	                rs.getString("TenTV"),
	                rs.getString("SoDienThoai"),
	                rs.getString("NhomMau")
	            });
	        }

	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Create the frame.
	 */
	public ThanhVienForm() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Quản Lý Tình Nguyện Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 799);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 255, 255));
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setFont(new Font("Arial", Font.ITALIC, 18));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 991, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Cùng nhau làm nên những điều ý nghĩa!");
		lblNewLabel_1.setFont(new Font("Arial", Font.ITALIC, 18));
		lblNewLabel_1.setIgnoreRepaint(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(new Rectangle(228, 72, 400, 30));
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 255, 255));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ TÌNH NGUYỆN VIÊN");
		lblNewLabel.setBounds(new Rectangle(218, 22, 600, 50));
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 32));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\BTLjava\\Screenshot 2026-05-29 004816.png"));
		lblNewLabel_2.setBounds(691, 11, 300, 120);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_1.setBounds(new Rectangle(10, 10, 10, 10));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 128, 1008, 304);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("D:\\BTLjava\\Screenshot 2026-05-29 004831.png"));
		lblNewLabel_3.setBounds(31, 39, 40, 31);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\BTLjava\\Screenshot 2026-05-29 004834.png"));
		lblNewLabel_4.setBounds(37, 81, 40, 28);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("D:\\BTLjava\\Screenshot 2026-05-29 004839.png"));
		lblNewLabel_5.setBounds(38, 120, 30, 32);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("D:\\BTLjava\\Screenshot 2026-05-29 004843.png"));
		lblNewLabel_6.setBounds(41, 163, 30, 31);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Mã TV:");
		lblNewLabel_7.setBounds(93, 50, 48, 14);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Tên TV:");
		lblNewLabel_8.setBounds(93, 88, 48, 14);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("SĐT:");
		lblNewLabel_9.setBounds(93, 129, 48, 14);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Nhóm máu:");
		lblNewLabel_10.setBounds(93, 171, 77, 14);
		panel_1.add(lblNewLabel_10);
		
		txtMaTV = new JTextField();
		txtMaTV.setBounds(177, 47, 231, 25);
		panel_1.add(txtMaTV);
		txtMaTV.setColumns(10);
		
		txtTenTV = new JTextField();
		txtTenTV.setBounds(177, 85, 231, 25);
		panel_1.add(txtTenTV);
		txtTenTV.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(177, 126, 231, 25);
		panel_1.add(txtSDT);
		txtSDT.setColumns(10);
		
		JComboBox cboNhomMau = new JComboBox();
		cboNhomMau.setModel(new DefaultComboBoxModel(new String[] {"A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-"}));
		cboNhomMau.setBounds(177, 167, 128, 22);
		panel_1.add(cboNhomMau);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon("D:\\BTLjava\\Screenshot 2026-05-29 004823.png"));
		lblNewLabel_11.setBounds(636, 21, 425, 225);
		panel_1.add(lblNewLabel_11);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(txtMaTV.getText().trim().isEmpty()) {
			            JOptionPane.showMessageDialog(
			                    null,
			                    "Mã TV không được để trống!");
			            txtMaTV.requestFocus();
			            return;
			        }

			        if(txtTenTV.getText().trim().isEmpty()) {
			            JOptionPane.showMessageDialog(
			                    null,
			                    "Tên TV không được để trống!");
			            txtTenTV.requestFocus();
			            return;
			        }

			        if(txtSDT.getText().trim().isEmpty()) {
			            JOptionPane.showMessageDialog(
			                    null,
			                    "SĐT không được để trống!");
			            txtSDT.requestFocus();
			            return;
			        }

				 try {

				        Connection con = DAO.getConnection();
				        String check =
				                "SELECT * FROM ThanhVien WHERE MaTV=?";

				        PreparedStatement pstCheck =
				                con.prepareStatement(check);

				        pstCheck.setString(1, txtMaTV.getText());

				        ResultSet rs =
				                pstCheck.executeQuery();

				        if(rs.next()) {

				            JOptionPane.showMessageDialog(
				                    null,
				                    "Mã TV đã tồn tại!");

				            return;
				        }

				        String sql =
				                "INSERT INTO ThanhVien(MaTV,TenTV,SoDienThoai,NhomMau) VALUES(?,?,?,?)";

				        PreparedStatement pst = con.prepareStatement(sql);

				        pst.setString(1, txtMaTV.getText());
				        pst.setString(2, txtTenTV.getText());
				        pst.setString(3, txtSDT.getText());
				        pst.setString(4, cboNhomMau.getSelectedItem().toString());

				        pst.executeUpdate();

				        JOptionPane.showMessageDialog(null,
				                "Thêm thành công!");

				        loadData();

				        con.close();

				    } catch (Exception e1) {

				        JOptionPane.showMessageDialog(null,
				                "Thêm thất bại!");
				    }
			}
		});
		
		btnNewButton.setBounds(43, 240, 116, 31);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {

			            Connection con =
			                    DAO.getConnection();

			            String sql =
			                    "UPDATE ThanhVien "
			                  + "SET TenTV=?, SoDienThoai=?, NhomMau=? "
			                  + "WHERE MaTV=?";

			            PreparedStatement pst =
			                    con.prepareStatement(sql);

			            pst.setString(1,
			                    txtTenTV.getText());

			            pst.setString(2,
			                    txtSDT.getText());

			            pst.setString(3,
			                    cboNhomMau.getSelectedItem().toString());

			            pst.setString(4,
			                    txtMaTV.getText());

			            pst.executeUpdate();

			            JOptionPane.showMessageDialog(
			                    null,
			                    "Sửa thành công");

			            loadData();

			            con.close();

			        } catch(Exception ex) {

			            ex.printStackTrace();
			        }
			}
		});
		btnNewButton_1.setBounds(181, 240, 124, 31);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {

			            Connection con =
			                    DAO.getConnection();

			            String sql =
			                    "DELETE FROM ThanhVien WHERE MaTV=?";

			            PreparedStatement pst =
			                    con.prepareStatement(sql);

			            pst.setString(1,
			                    txtMaTV.getText());

			            pst.executeUpdate();

			            JOptionPane.showMessageDialog(
			                    null,
			                    "Xóa thành công");

			            loadData();

			            con.close();

			        } catch(Exception ex) {

			            ex.printStackTrace();
			        }
			    
			}
		});
		btnNewButton_2.setBounds(332, 240, 128, 31);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Làm mới");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaTV.setText("");
				txtTenTV.setText("");
				txtSDT.setText("");

				cboNhomMau.setSelectedIndex(0);
			}
		});
		btnNewButton_3.setBounds(480, 240, 128, 31);
		panel_1.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 433, 1008, 329);
		contentPane.add(scrollPane);
		
		tblThanhVien = new JTable();
		tblThanhVien.setRowHeight(25);
		scrollPane.setViewportView(tblThanhVien);
		
		tblThanhVien.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 TV", "T\u00EAn TV", "S\u0110T", "Nh\u00F3m m\u00E1u"
			}
		));
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, panel, lblNewLabel, lblNewLabel_1, lblNewLabel_2, panel_1, tblThanhVien, scrollPane, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5, lblNewLabel_6, lblNewLabel_7, lblNewLabel_8, lblNewLabel_9, lblNewLabel_10, txtMaTV, txtTenTV, txtSDT, cboNhomMau, lblNewLabel_11, btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3}));

	}

}

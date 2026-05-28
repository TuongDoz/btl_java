package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConection {
	// Địa chỉ chọc vào MySQL trên máy bạn (localhost)
    private static final String URL = "jdbc:mysql://localhost:3306/QuanLyTinhNguyen?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Kết nối thất bại! Hãy kiểm tra lại thư viện hoặc XAMPP.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Chúc mừng Nhóm trưởng! Đã kết nối Java với MySQL thành công rực rỡ!");
        }
    }
}

package thanhvienform;
import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	 private static final String URL =
	            "jdbc:mysql://localhost:3306/QuanLyTinhNguyen?useUnicode=true&characterEncoding=UTF-8";

	    private static final String USER = "root";

	    private static final String PASSWORD = "";

	    public static Connection getConnection() {

	        try {

	            Class.forName("com.mysql.cj.jdbc.Driver");

	            return DriverManager.getConnection(
	                    URL,
	                    USER,
	                    PASSWORD);

	        } catch (Exception e) {

	            e.printStackTrace();
	        }

	        return null;

}
	    public static void main(String[] args) {

	        Connection con = DAO.getConnection();

	        if(con != null) {
	            System.out.println("Kết nối thành công");
	        } else {
	            System.out.println("Kết nối thất bại");
	        }
	    }
}
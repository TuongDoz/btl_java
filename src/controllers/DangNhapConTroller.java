package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DatabaseConnection;

public class DangNhapConTroller {
	public boolean Ktra(String taiKhoan,String matKhau) {
		try(Connection con = DatabaseConnection.getConnection()){
			if(con != null) {
				String sql = "Select * From TaiKhoan Where TenDangNhap= ? and MatKhau =?";
				PreparedStatement p = con.prepareStatement(sql);
				p.setString(1, taiKhoan);
				p.setString(2, matKhau);
				ResultSet r = p.executeQuery();
                return r.next();
			}
		} catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
	
}
